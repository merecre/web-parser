package crawler.core.usecase;

import java.util.Collection;
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
        final Set<String> urls = webModel.getUrls();

        urls.stream()
                .map(url -> autoAdvertisementUseCaseWebGateway.getAutoAdvertisements(url))
                .flatMap(Collection::stream)
                .filter(a->!autoAdvertisementUseCaseDBGateway.isExistByLink(a))
                .forEach(a->autoAdvertisementUseCaseDBGateway.persistSingle(a));
        return Boolean.TRUE;
    }
}
