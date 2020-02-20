package crawler.services;

import crawler.core.entities.AutoFeature;
import crawler.services.repository.AutoFeatureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AutoFeatureService {
    @Autowired
    private AutoFeatureRepository autoFeatureRepository;

    public void save(AutoFeature autoFeature) {
        autoFeatureRepository
                .findById(autoFeature.getName())
                .orElse(autoFeatureRepository.save(autoFeature));
    }
}
