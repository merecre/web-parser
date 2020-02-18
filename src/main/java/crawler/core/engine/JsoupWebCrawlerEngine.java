package crawler.core.engine;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Created by DMC on 10/21/2019.
 */
public class JsoupWebCrawlerEngine implements WebCrawlerEngine<Document> {

    @Override
    public Document parse(String url) {
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            throw new RuntimeException("Connection error.");
        }
    }
}
