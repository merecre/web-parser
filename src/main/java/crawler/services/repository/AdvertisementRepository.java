package crawler.services.repository;

import crawler.core.entities.Advertisement;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DMC on 10/31/2019.
 */
public interface AdvertisementRepository extends CrudRepository<Advertisement, Long> {

}
