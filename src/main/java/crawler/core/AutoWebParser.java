package crawler.core;

import crawler.core.composer.AdvertisementComposer;
import crawler.core.composer.AutoAdvertisementComposer;
import crawler.core.engine.WebCrawlerEngine;
import crawler.core.entities.Advertisement;
import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by DMC on 10/21/2019.
 */
public class AutoWebParser implements WebParser<List<AutoAdvertisement>, String> {

    public final static String URL = "https://www.ss.lv/ru/transport/cars/";
    public final static String URL_CAR_MODEL_SELECTOR = "a.a_category";
    public final static String URL_CAR_SELECTOR = "a.am";
    public final static String URL_FILTER_SELL = "sell/";

    private WebCrawlerEngine<Document> engine;

    private AutoAdvertisementComposer composer;

    public AutoWebParser(WebCrawlerEngine<Document> engine, AutoAdvertisementComposer composer) {
        this.engine = engine;
        this.composer = composer;
    }

    @Override
    public List<AutoAdvertisement> parse(String url) {

        List<AutoAdvertisement> advertisements = new ArrayList<>();

        Elements carModelLinks = composeLinksFromDocument(url, URL_CAR_MODEL_SELECTOR);
        for (Element link : carModelLinks) {
            String carModelUrl = link.absUrl("href")+URL_FILTER_SELL;
            //System.out.println("Model: " + carModelUrl);
            Elements carLinks = composeLinksFromDocument(carModelUrl, URL_CAR_SELECTOR);
            for (Element carLink : carLinks) {
                String carUrl = carLink.absUrl("href");
                //System.out.println("Car: " + carUrl);
                Document document = engine.parse(carUrl);
                advertisements.add(composeAdvertisement(document, carUrl));
            }
        }

        return advertisements;
    }

    private Elements composeLinksFromDocument(String url, String filter) {
        return engine.parse(url).select(filter);
    }

    private AutoAdvertisement composeAdvertisement(Document document, String carUrl) {
        return composer.compose(document, carUrl);
    }
}
