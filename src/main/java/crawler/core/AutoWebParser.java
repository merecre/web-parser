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
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by DMC on 10/21/2019.
 */
public class AutoWebParser implements WebParser<Set<AutoAdvertisement>, String> {

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
    public Set<AutoAdvertisement> parse(String url) {

        final Elements carModelLinks = composeLinksFromDocument(url, URL_CAR_MODEL_SELECTOR);
        return carModelLinks.stream()
                .map(link -> link.absUrl("href")+URL_FILTER_SELL)
                .map(surl -> composeLinksFromDocument(surl, URL_CAR_SELECTOR))
                .flatMap(Collection::stream)
                .map(l->l.absUrl("href"))
                .map(surl -> engine.parse(surl))
                .map(document -> composeAdvertisement(document))
                .collect(Collectors.toSet());
    }

    private Elements composeLinksFromDocument(String url, String filter) {
        return engine.parse(url).select(filter);
    }

    private AutoAdvertisement composeAdvertisement(Document document, String carUrl) {
        return composer.compose(document);
    }

    private AutoAdvertisement composeAdvertisement(Document document) {
        return composer.compose(document);
    }
}
