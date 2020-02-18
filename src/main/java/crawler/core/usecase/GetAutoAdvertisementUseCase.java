package crawler.core.usecase;

import crawler.core.WebParser;
import crawler.core.entities.AutoAdvertisement;

import java.util.List;

public class GetAutoAdvertisementUseCase {
    private WebParser<List<AutoAdvertisement>, String> parser;

    public GetAutoAdvertisementUseCase(WebParser<List<AutoAdvertisement>, String> parser) {
        this.parser = parser;
    }

    public List<AutoAdvertisement> parse (String url) {
        return parser.parse(url);
    }
}
