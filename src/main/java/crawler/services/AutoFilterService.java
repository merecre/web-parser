package crawler.services;

import crawler.core.entities.AutoAdvertisement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutoFilterService {

    List<AutoAdvertisement> getAutoAdvertisementByModel(List<AutoAdvertisement> autoAdvertisement, String model) {
        return autoAdvertisement.stream()
                .filter(a -> a.getAuto().getModel().contains(model))
                .collect(Collectors.toList());
    }
}
