package ru.stepanov.OpticaStore.models;

import jakarta.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "lensescatalog")
public class LensesCatalog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Integer price;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Column(name = "dateadded")
    private Date dateAdded;

    public LensesCatalog() {}

    public LensesCatalog(String name, Integer price, Status status, Date dateAdded) {
        this.name = name;
        this.price = price;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }
}

enum Status {
    AVAILABLE, NOTAVAILABLE
}
