package crawler.services;

import crawler.core.usecase.UseCaseCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ScheduleService {

    private static final Logger logger = LoggerFactory.getLogger(ScheduleService.class);

    @Autowired
    private List<UseCaseCommand<Boolean>> businessFlowCommands;

    @Scheduled(fixedRate = 1000*60*60)
    public void run() {
        logger.info("Scheduled task is being started.");
        populateAutoAdvertisementsAndSendNotification();
    }

    private void populateAutoAdvertisementsAndSendNotification() {
        businessFlowCommands.forEach(UseCaseCommand::execute);
    }
}
