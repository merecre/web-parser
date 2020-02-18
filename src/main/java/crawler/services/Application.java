package crawler.services;

import crawler.core.AutoWebParser;
import crawler.core.WebParser;
import crawler.core.composer.AutoAdvertisementComposer;
import crawler.core.engine.JsoupWebCrawlerEngine;
import crawler.core.entities.AutoAdvertisement;
import crawler.services.repository.AdvertisementRepository;
import crawler.services.repository.AutoAdvertisementRepository;
import crawler.services.repository.AutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by DMC on 10/21/2019.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"crawler.core", "crawler.services"})
@EntityScan("crawler.core.entities")
public class Application implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AutoFilterService autoFilterService;

    @Autowired
    private
    AutoAdvertisementRepository repository;

    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        WebParser<List<AutoAdvertisement>, String> parser =
                new AutoWebParser(new JsoupWebCrawlerEngine(), new AutoAdvertisementComposer());

        final String url = URLParameters.buildURLFromArguments(args);
        System.out.println("URL: " + url);
        List<AutoAdvertisement> autoAdvertisement = parser.parse(url);

        if (URLParameters.getModel() != null) {
            final String model = URLParameters.getModel().toUpperCase();
            List<AutoAdvertisement> advertisements = autoFilterService
                   .getAutoAdvertisementByModel(autoAdvertisement, model);
            advertisements.forEach(a -> {
                repository.save(a);
                System.out.println("Advertisement:");
                System.out.println("Link:" + a.getLink());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                System.out.println("Publish date:" + a.getPublishDate().format(formatter));
                System.out.println("Model:" + a.getAuto().getModel());
                System.out.println("Price:" + a.getPrice());
            });
        } else {
            autoAdvertisement.forEach(a -> {
                repository.save(a);
                System.out.println("Advertisement:");
                System.out.println("Link:" + a.getLink());
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
                System.out.println("Publish date:" + a.getPublishDate().format(formatter));
                System.out.println("Model:" + a.getAuto().getModel());
                System.out.println("Price:" + a.getPrice());
            });
        }
    }
}
