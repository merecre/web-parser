package crawler.core.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by DMC on 10/22/2019.
 */
@Entity
@Table(name = "advertisement")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Advertisement {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private String price;

    private String currency;

    private String link;

    private java.time.LocalDateTime publishDate;

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Advertisement{" +
                "Id=" + id +
                ", price='" + price + '\'' +
                ", currency='" + currency + '\'' +
                ", link='" + link + '\'' +
                ", publishDate='" + publishDate + '\'' +
                '}';
    }
}
