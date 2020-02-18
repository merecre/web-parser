package crawler.core.entities;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Created by DMC on 10/21/2019.
 */

@Entity
@Table(name = "auto")
public class Auto {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private long id;

    @OneToMany(mappedBy="auto")
    private Set<AutoAdvertisement> advertisements;

    private String model;

    private String mileage;

    private String registrationDate;

    private String engine;

    private String transmission;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Set<AutoAdvertisement> getAdvertisements() {
        return advertisements;
    }

    public void setAdvertisements(Set<AutoAdvertisement> advertisements) {
        this.advertisements = advertisements;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getMileage() {
        return mileage;
    }

    public void setMileage(String mileage) {
        this.mileage = mileage;
    }

    public String getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(String registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getEngine() {
        return engine;
    }

    public void setEngine(String engine) {
        this.engine = engine;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    @Override
    public String toString() {
        return "Auto{" +
                "Id=" + id +
                ", advertisements=" + advertisements +
                ", model='" + model + '\'' +
                '}';
    }
}
