package crawler.core.entities;

import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.Id;

import javax.persistence.*;

/**
 * Created by DMC on 10/24/2019.
 */
@Entity
public class AutoAdvertisement extends Advertisement {

    @ManyToOne
    @Cascade(value={org.hibernate.annotations.CascadeType.PERSIST})
    @JoinColumn(name = "auto_id", nullable = false)
    private Auto auto;

    public Auto getAuto() {
        return auto;
    }

    public void setAuto(Auto auto) {
        this.auto = auto;
    }

    @Override
    public String toString() {
        return super.toString() + '\'' +
        "AutoAdvertisement{" +
                "auto=" + auto +
                '}';
    }
}
