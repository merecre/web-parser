package crawler.configuration;

import crawler.services.gateway.AutoAdvertisementWebGateway;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public AutoAdvertisementWebGateway autoAdvertisementWebGateway() {
        return new AutoAdvertisementWebGateway();
    }
}
