package crawler.configuration;

import crawler.core.usecase.GetAutoAdvertisementFromWebUseCase;
import crawler.core.usecase.SendNotificationAboutNewCarAdvertisementUseCase;
import crawler.core.usecase.UseCaseCommand;
import crawler.core.usecase.WebModel;
import crawler.services.AutoAdvertisementService;
import crawler.services.URLParameterService;
import crawler.services.gateway.AutoAdvertisementWebGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Autowired
    private ApplicationArguments args;

    @Autowired
    private AutoAdvertisementService autoAdvertisementDBGateway;

    @Bean
    public WebModel webModel() {
        URLParameterService.buildURLFromArguments(args);
        return URLParameterService.getWebModel();
    }

    @Bean
    public AutoAdvertisementWebGateway autoAdvertisementWebGateway() {
        return new AutoAdvertisementWebGateway();
    }

    @Bean(name = "populateAutoAdvertisement")
    @Order(1)
    public UseCaseCommand<Boolean> populateAutoAdvertisementUseCase(AutoAdvertisementWebGateway autoAdvertisementWebGateway,
                                                                    WebModel webModel) {
            return new GetAutoAdvertisementFromWebUseCase(autoAdvertisementWebGateway, autoAdvertisementDBGateway, webModel);
    }

    @Bean(name = "senNotificationAboutNewAutoAdvertisement")
    @Order(2)
    public UseCaseCommand<Boolean> sendNotificationAboutNewCarAdvertisementUseCase() {
        return new SendNotificationAboutNewCarAdvertisementUseCase(autoAdvertisementDBGateway);
    }
}
