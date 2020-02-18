package crawler.services.repository;

import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;

import java.util.List;

/**
 * Created by DMC on 10/31/2019.
 */
public interface AutoAdvertisementRepository extends AdvertisementRepository {
    List<AutoAdvertisement> findByAuto(Auto auto);
}
