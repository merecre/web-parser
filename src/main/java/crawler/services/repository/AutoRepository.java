package crawler.services.repository;

import crawler.core.entities.Advertisement;
import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by DMC on 10/31/2019.
 */
public interface AutoRepository extends CrudRepository<Auto, Long> {
    Auto findByModel(String model);
}
