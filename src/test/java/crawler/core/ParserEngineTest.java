package crawler.core;

import crawler.core.AutoWebParser;
import crawler.core.WebParser;
import crawler.core.composer.AutoAdvertisementComposer;
import crawler.core.engine.JsoupWebCrawlerEngine;
import crawler.core.entities.Advertisement;
import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

/**
 * Created by DMC on 10/21/2019.
 */
public class ParserEngineTest {

    @Test
    public void test() throws IOException {
        Document doc = Jsoup.connect("http://en.wikipedia.org/").get();

        Elements newsHeadlines = doc.select("#mp-itn b a");
        for (Element headline : newsHeadlines) {
            System.out.println(headline.absUrl("href"));
        }
    }

    @Test
    public void autoWebParserTest() {
        WebParser<List<AutoAdvertisement>, String> parser =
                new AutoWebParser(new JsoupWebCrawlerEngine(), new AutoAdvertisementComposer());

        List<AutoAdvertisement> autoAdvertisement = parser.parse(AutoWebParser.URL+"audi/");
        autoAdvertisement.forEach(a ->System.out.println("Advertisement:" + a));
    }
}
