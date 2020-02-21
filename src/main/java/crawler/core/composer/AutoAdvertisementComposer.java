package crawler.core.composer;

import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import crawler.core.entities.AutoFeature;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.math.BigInteger;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by DMC on 10/22/2019.
 */
public class AutoAdvertisementComposer implements AdvertisementComposer<Document> {

    @Override
    public AutoAdvertisement compose(Document document) {
        AutoAdvertisement advertisement = new AutoAdvertisement();

        Elements publishDate = document.select("td.msg_footer").select("td:contains(Дата: )");
        Elements carPrice = document.select("span.ads_price");
        Elements carModel = document.select("td#tdo_31");
        Elements mileage =  document.select("td#tdo_16");
        Elements registrationDate = document.select("td#tdo_18");
        Elements engineElement = document.select("td#tdo_15");
        Elements transmission = document.select("td#tdo_35");
        Elements featureElements = document.select("b.auto_c");

        Set<AutoFeature> features = featureElements
                .stream()
                .map(e -> new AutoFeature(e.text()))
                .collect(Collectors.toSet());

        Auto auto = new Auto();
        auto.setModel(carModel.first().text());
        auto.setMileage(formatToNumeric(mileage.hasText()?mileage.first().text():"0"));
        auto.setRegistrationDate(formatToYear(registrationDate.first().text()));

        auto.setEngine(engineElement.hasText()?(engineElement.first().text()).split("\\s+")[0]:"?");
        auto.setEngineType(engineElement.hasText()?(engineElement.first().text()).split("\\s+")[1]:"?");

        auto.setTransmission(transmission.first().text());
        auto.setFeatures(features);

        advertisement.setLink(document.location());
        advertisement.setPublishDate(formatToDatetime(publishDate.first().text()));
        advertisement.setPrice(formatToNumeric(carPrice.first().text()));
        advertisement.setAuto(auto);

        return advertisement;
    }

    private LocalDateTime formatToDatetime (String publishDate) {
        final String dateTime = publishDate.substring(6);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private LocalDate formatToYear(String registrationDate) {
        final String dateTime = registrationDate.substring(0,4);
        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .appendPattern("yyyy")
                .parseDefaulting(ChronoField.MONTH_OF_YEAR, 1)
                .parseDefaulting(ChronoField.DAY_OF_MONTH, 1)
                .toFormatter();

        return LocalDate.parse(dateTime, format);
    }

    private BigInteger formatToNumeric (String string) {
        final String pureNumeric = string.replaceAll("[^\\d.]", "");
        return new BigInteger(pureNumeric);
    }
}
