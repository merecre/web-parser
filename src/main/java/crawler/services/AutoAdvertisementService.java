package crawler.services;

import crawler.core.entities.AutoAdvertisement;
import crawler.core.entities.AutoFeature;
import crawler.core.usecase.AutoAdvertisementUseCaseDBGateway;
import crawler.services.repository.AutoAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Set;

@Component
public class AutoAdvertisementService implements AutoAdvertisementUseCaseDBGateway {

    @Autowired
    private AutoAdvertisementRepository repository;

    @Autowired
    private AutoFeatureService autoFeatureService;

    @Transactional
    public void saveAll(Collection<AutoAdvertisement> autoAdvertisement) {
        autoAdvertisement.forEach(a -> {
            save(a);
            System.out.println("Advertisement:");
            System.out.println("Link:" + a.getLink());
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
            System.out.println("Publish date:" + a.getPublishDate().format(formatter));
            System.out.println("Model:" + a.getAuto().getModel());
            System.out.println("Price:" + a.getPrice());
        });
    }

    @Transactional
    public void save(AutoAdvertisement autoAdvertisement) {
        Set<AutoFeature> autoFeatureSet = autoAdvertisement.getAuto().getFeatures();
        autoFeatureSet.forEach(autoFeature -> autoFeatureService.save(autoFeature));
        repository.save(autoAdvertisement);
    }

    public Set<AutoAdvertisement> getAll() {
        return repository.findAll();
    }

    @Override
    public Set<AutoAdvertisement> getNewestAutoAdvertisement() {
        LocalDate localDate = LocalDate.now();
        LocalDateTime startDateTime = localDate.atStartOfDay();
        LocalDateTime endDateTime = localDate.atTime(LocalTime.MAX);

        return repository.findByPublishDateBetween(startDateTime, endDateTime);
    }

    @Override
    public void persistAll(Set<AutoAdvertisement> autoAdvertisements) {
        saveAll(autoAdvertisements);
    }

    @Override
    public void persistSingle(AutoAdvertisement autoAdvertisement) {
        save(autoAdvertisement);
    }

    public Boolean isExistByLink(AutoAdvertisement autoAdvertisement) {
        return repository.findByLink(autoAdvertisement.getLink()) != null;
    }
}
