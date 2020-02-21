package crawler.core.usecase;

import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import crawler.services.notification.Mail;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SendNotificationAboutNewCarAdvertisementUseCase implements UseCaseCommand<Boolean> {

    AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway;
    NotificationService<Mail> notificationService;

    public SendNotificationAboutNewCarAdvertisementUseCase(AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway,
                                                           NotificationService<Mail> notificationService) {
        this.autoAdvertisementUseCaseDBGateway = autoAdvertisementUseCaseDBGateway;
        this.notificationService = notificationService;
    }

    @Override
    public Boolean execute() {
        Set<AutoAdvertisement> autoAdvertisements = autoAdvertisementUseCaseDBGateway.getNewestAutoAdvertisement();
        System.out.println("DB Records: "+ autoAdvertisements.size());
        List<AutoAdvertisement> filteredAutoAdvertisements = autoAdvertisements.stream()
                .filter(a -> a.getVisited()==null || !a.getVisited())
                .collect(Collectors.toList());

        filteredAutoAdvertisements.forEach(r -> {
            sendNotification(r);
            r.setVisited(true);
            autoAdvertisementUseCaseDBGateway.persistSingle(r);
        });

        return Boolean.TRUE;
    }

    private void sendNotification(AutoAdvertisement autoAdvertisement) {
        final Auto auto = autoAdvertisement.getAuto();
        Mail mail = new Mail("New auto: " + auto.getModel());
        final String text = "Auto: " + auto.getModel() + ". Mileage : " + auto.getMileage() + ". Price: " + autoAdvertisement.getPrice() + "\n" +
                "link : " + autoAdvertisement.getLink();
        mail.setText(text);
        notificationService.notify(mail);
    }
}
