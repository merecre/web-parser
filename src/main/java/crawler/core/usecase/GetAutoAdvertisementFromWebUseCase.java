package crawler.core.usecase;

import crawler.core.WebParser;
import crawler.core.entities.AutoAdvertisement;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public class GetAutoAdvertisementFromWebUseCase implements UseCaseCommand<Boolean>{

    AutoAdvertisementUseCaseWebGateway autoAdvertisementUseCaseWebGateway;
    AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway;
    WebModel webModel;

    public GetAutoAdvertisementFromWebUseCase(AutoAdvertisementUseCaseWebGateway autoAdvertisementUseCaseWebGateway,
                                              AutoAdvertisementUseCaseDBGateway autoAdvertisementUseCaseDBGateway, WebModel webModel) {
        this.autoAdvertisementUseCaseWebGateway = autoAdvertisementUseCaseWebGateway;
        this.autoAdvertisementUseCaseDBGateway = autoAdvertisementUseCaseDBGateway;
        this.webModel = webModel;
    }

    public Boolean execute() {
        Set<String> urls = webModel.getUrls();
        urls.stream().forEach(url -> {
                Set<AutoAdvertisement> autoAdvertisement = autoAdvertisementUseCaseWebGateway.getAutoAdvertisements(url);
                if (!autoAdvertisement.isEmpty()) {
                    autoAdvertisementUseCaseDBGateway.persistAll(autoAdvertisement);
                }
            });

        return Boolean.TRUE;
    }
}
