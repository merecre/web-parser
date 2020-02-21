package crawler.core.usecase;

import crawler.core.entities.AutoAdvertisement;

import java.util.Set;

public interface AutoAdvertisementUseCaseDBGateway {
    Set<AutoAdvertisement> getNewestAutoAdvertisement();
    void persistAll(Set<AutoAdvertisement> autoAdvertisements);
    void persistSingle(AutoAdvertisement autoAdvertisement);
    Boolean isExistByLink(AutoAdvertisement autoAdvertisement);
}
