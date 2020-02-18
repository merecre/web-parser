package crawler.core.composer;

import crawler.core.entities.Advertisement;
import org.jsoup.nodes.Document;

/**
 * Created by DMC on 10/22/2019.
 */
public interface AdvertisementComposer<T> {
    Advertisement compose(Document document);
}
