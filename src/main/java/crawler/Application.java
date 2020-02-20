package crawler;

import crawler.core.usecase.UseCaseCommand;
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

import java.util.List;

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
    private List<UseCaseCommand<Boolean>> businessFlowCommands;

    public Application(List<UseCaseCommand<Boolean>> businessFlowCommands) {
        this.businessFlowCommands = businessFlowCommands;
    }

    public static void main(String... args) throws Exception {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateAutoAdvertisementsAndSendNotification();
    }

    private void populateAutoAdvertisementsAndSendNotification() {
        businessFlowCommands.forEach(UseCaseCommand::execute);
    }
}
