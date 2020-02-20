package crawler.core.usecase;

import crawler.core.entities.AutoAdvertisement;

import java.util.Set;

public interface AutoAdvertisementUseCaseWebGateway {
    Set<AutoAdvertisement> getAutoAdvertisements(String url);
}
