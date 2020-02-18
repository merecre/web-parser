package crawler.core.composer;

import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        Elements engine = document.select("td#tdo_15");
        Elements transmission = document.select("td#tdo_35");
        Elements features = document.select("b.auto_c");

        System.out.println("Car price: " + carPrice.first().text());
        System.out.println("Car model: " + carModel.first().text());
        System.out.println("Publish Date: " + publishDate.first().text());
        System.out.println("Registration Date: " + registrationDate.first().text());
        if (mileage.hasText()) {
            System.out.println("mileage: " + mileage.first().text());
        }

        //features.forEach(f -> System.out.println("Feature:"+f.text()));

        Auto auto = new Auto();
        auto.setModel(carModel.first().text());
        auto.setMileage(mileage.hasText()?mileage.first().text():"?");
        auto.setRegistrationDate(registrationDate.first().text());
        auto.setEngine(engine.hasText()?engine.first().text():"?");
        auto.setTransmission(transmission.first().text());

        advertisement.setLink(document.location());
        advertisement.setPublishDate(formatPublishDate(publishDate.first().text()));
        advertisement.setPrice(carPrice.first().text());
        advertisement.setAuto(auto);

        return advertisement;
    }

    private LocalDateTime formatPublishDate (String publishDate) {
        final String dateTime = publishDate.substring(6);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return LocalDateTime.parse(dateTime, formatter);
    }

    private LocalDateTime formatRegistrationDate (String registrationDate) {
        final String dateTime = registrationDate.substring(0,4);
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        return LocalDateTime.parse(dateTime, formatter);
    }
}
