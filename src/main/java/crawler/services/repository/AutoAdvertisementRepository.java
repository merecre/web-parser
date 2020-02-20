package crawler.services.repository;

import crawler.core.entities.Auto;
import crawler.core.entities.AutoAdvertisement;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

/**
 * Created by DMC on 10/31/2019.
 */
public interface AutoAdvertisementRepository extends CrudRepository<AutoAdvertisement, Long> {
    List<AutoAdvertisement> findByAuto(Auto auto);

    @Query("SELECT ad, auto, feature FROM AutoAdvertisement ad LEFT JOIN ad.auto auto LEFT JOIN fetch auto.features feature")
    Set<AutoAdvertisement> findAll();

    @Query("SELECT ad, auto, feature FROM AutoAdvertisement ad LEFT JOIN ad.auto auto LEFT JOIN fetch auto.features feature" +
            " WHERE ad.publishDate BETWEEN :startDate AND :endDate")
    Set<AutoAdvertisement> findByPublishDateBetween(@Param("startDate")LocalDateTime startDateTime, @Param("endDate")LocalDateTime endDate);
}
