package crawler.services.repository;

import crawler.core.entities.Auto;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by DMC on 10/31/2019.
 */
public interface AutoRepository extends CrudRepository<Auto, Long> {
    Auto findByModel(String model);
}
