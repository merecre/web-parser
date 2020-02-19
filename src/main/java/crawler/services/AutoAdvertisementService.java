package crawler.services;

import crawler.core.entities.AutoAdvertisement;
import crawler.core.entities.AutoFeature;
import crawler.services.repository.AutoAdvertisementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

@Component
public class AutoAdvertisementService {

    @Autowired
    private AutoAdvertisementRepository repository;

    @Autowired
    private AutoFeatureService autoFeatureService;

    public void saveAll(List<AutoAdvertisement> autoAdvertisement) {
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

    public void save(AutoAdvertisement autoAdvertisement) {
        Set<AutoFeature> autoFeatureSet = autoAdvertisement.getAuto().getFeatures();
        autoFeatureSet.forEach(autoFeature ->  autoFeatureService.save(autoFeature));
        repository.save(autoAdvertisement);
    }

    public Set<AutoAdvertisement> getAll() {
        return repository.findAll();
    }
}
