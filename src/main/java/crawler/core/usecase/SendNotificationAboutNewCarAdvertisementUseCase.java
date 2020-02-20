package crawler.core.usecase;

import crawler.core.entities.AutoAdvertisement;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class SendNotificationAboutNewCarAdvertisementUseCase implements UseCaseCommand<Boolean> {

    AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway;

    public SendNotificationAboutNewCarAdvertisementUseCase(AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway) {
        this.autoAdvertisementUseCaseDBGateway = autoAdvertisementUseCaseDBGateway;
    }

    @Override
    public Boolean execute() {

        Set<AutoAdvertisement> autoAdvertisements = autoAdvertisementUseCaseDBGateway.getNewestAutoAdvertisement();
        System.out.println("DB Records: "+ autoAdvertisements.size());
        List<AutoAdvertisement> filteredAutoAdvertisements = autoAdvertisements.stream()
                .filter(a -> a.getVisited()==null || !a.getVisited())
                .collect(Collectors.toList());

        filteredAutoAdvertisements.forEach(r -> {
            // TODO send notification
            r.setVisited(true);
            autoAdvertisementUseCaseDBGateway.persistSingle(r);
        });

        return Boolean.TRUE;
    }
}
