package crawler;

import crawler.core.AutoWebParser;
import crawler.core.WebParser;
import crawler.core.composer.AutoAdvertisementComposer;
import crawler.core.engine.JsoupWebCrawlerEngine;
import crawler.core.entities.AutoAdvertisement;
import crawler.core.usecase.GetAutoAdvertisementFilteredByModelUseCase;
import crawler.core.usecase.GetAutoAdvertisementUseCase;
import crawler.services.AutoAdvertisementService;
import crawler.services.URLParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

import java.util.List;

/**
 * Created by DMC on 10/21/2019.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"crawler.core", "crawler.services"})
@EntityScan("crawler.core.entities")
public class Application implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AutoAdvertisementService advertisementService;

    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        WebParser<List<AutoAdvertisement>, String> parser =
                new AutoWebParser(new JsoupWebCrawlerEngine(), new AutoAdvertisementComposer());
        GetAutoAdvertisementUseCase parseAutoAdvertisementUseCase = new GetAutoAdvertisementUseCase(parser);

        final String url = URLParameterService.buildURLFromArguments(args);
        List<AutoAdvertisement> autoAdvertisement = parseAutoAdvertisementUseCase.parse(url);

        if (URLParameterService.hasModel()) {
            GetAutoAdvertisementFilteredByModelUseCase getAutoAdvertisementFilteredByModelUseCase =
                    new GetAutoAdvertisementFilteredByModelUseCase();
            final String model = URLParameterService.getModel().toUpperCase();
            autoAdvertisement = getAutoAdvertisementFilteredByModelUseCase.getAutoAdvertisementByModel(autoAdvertisement, model);
        }

        if (!autoAdvertisement.isEmpty()) {
            advertisementService.saveAll(autoAdvertisement);
        }
    }
}
