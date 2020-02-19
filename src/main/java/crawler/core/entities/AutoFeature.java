package crawler.core.entities;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "auto_feature")
public class AutoFeature {

    @Id
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "features")
    private Set<Auto> autos;

    public AutoFeature() {}

    public AutoFeature(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AutoFeature that = (AutoFeature) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Auto> getAutos() {
        return autos;
    }

    public void setAutos(Set<Auto> autos) {
        this.autos = autos;
    }

    @Override
    public String toString() {
        return "AutoFeature{" +
                "name='" + name + '\'' +
                '}';
    }
}
