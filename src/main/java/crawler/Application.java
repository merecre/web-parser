package crawler;

import crawler.core.usecase.GetAutoAdvertisementFromWebUseCase;
import crawler.core.usecase.SendNotificationAboutNewCarAdvertisementUseCase;
import crawler.core.usecase.WebModel;
import crawler.services.AutoAdvertisementService;
import crawler.services.URLParameterService;
import crawler.services.gateway.AutoAdvertisementWebGateway;
import crawler.services.repository.AdvertisementRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Created by DMC on 10/21/2019.
 */

@SpringBootApplication
@ComponentScan(basePackages = {"crawler.configuration, crawler.core", "crawler.services"})
@EntityScan("crawler.core.entities")
@EnableScheduling
public class Application implements ApplicationRunner {
    private static final Logger logger = LoggerFactory.getLogger(Application.class);

    @Autowired
    private AdvertisementRepository advertisementRepository;

    @Autowired
    private AutoAdvertisementService autoAdvertisementDBGateway;

    @Autowired
    private AutoAdvertisementWebGateway autoAdvertisementWebGateway;

    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

        final WebModel webModel = URLParameterService.buildURLFromArguments(args);

        GetAutoAdvertisementFromWebUseCase parseAutoAdvertisementUseCase =
                new GetAutoAdvertisementFromWebUseCase(autoAdvertisementWebGateway, autoAdvertisementDBGateway, webModel);
        parseAutoAdvertisementUseCase.execute();

        SendNotificationAboutNewCarAdvertisementUseCase sendNotificationAboutNewCarAdvertisementUseCase =
                new SendNotificationAboutNewCarAdvertisementUseCase(autoAdvertisementDBGateway);
        sendNotificationAboutNewCarAdvertisementUseCase.execute();
    }
}
