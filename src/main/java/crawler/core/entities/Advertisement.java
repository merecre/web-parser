package crawler.core.entities;

import javax.persistence.*;
import java.math.BigInteger;
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

    BigInteger price;

    private String currency;

    private String link;

    private java.time.LocalDateTime publishDate;

    private Boolean visited;

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

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
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

    public Boolean getVisited() {
        return visited;
    }

    public void setVisited(Boolean visited) {
        this.visited = visited;
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
