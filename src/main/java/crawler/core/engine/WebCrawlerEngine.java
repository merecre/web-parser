package crawler.core.engine;

/**
 * Created by DMC on 10/21/2019.
 */
public interface WebCrawlerEngine<T> {
    T parse(String url);
}
