package crawler.core.usecase;

import crawler.core.WebParser;
import crawler.core.entities.AutoAdvertisement;

import java.util.List;

public class GetAutoAdvertisementUseCase {
    WebParser<List<AutoAdvertisement>, String> parser;
    String url;

    public GetAutoAdvertisementUseCase(WebParser<List<AutoAdvertisement>, String> parser, String url) {
        this.parser = parser;
        this.url = url;
    }

    public List<AutoAdvertisement> parse (WebParser<List<AutoAdvertisement>, String> parser, String url) {
        System.out.println("URL: " + url);
        return parser.parse(url);
    }
}
