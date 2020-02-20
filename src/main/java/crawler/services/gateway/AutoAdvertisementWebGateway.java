package crawler.services.gateway;

import crawler.core.AutoWebParser;
import crawler.core.WebParser;
import crawler.core.composer.AutoAdvertisementComposer;
import crawler.core.engine.JsoupWebCrawlerEngine;
import crawler.core.entities.AutoAdvertisement;
import crawler.core.usecase.AutoAdvertisementUseCaseWebGateway;
import java.util.Set;

public class AutoAdvertisementWebGateway implements AutoAdvertisementUseCaseWebGateway {
    @Override
    public Set<AutoAdvertisement> getAutoAdvertisements(String url) {
        WebParser<Set<AutoAdvertisement>, String> parser =
                new AutoWebParser(new JsoupWebCrawlerEngine(), new AutoAdvertisementComposer());
        return parser.parse(url);
    }
}
