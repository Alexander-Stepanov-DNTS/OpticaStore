package ru.stepanov.OpticaStore.DTO;

import java.util.Date;

public class FramesCatalogDTO {

    private Integer id;
    private String name;
    private Integer price;
    private String status;  // changed from Status enum to String for easier data handling
    private Date dateAdded;

    // Constructors
    public FramesCatalogDTO() {}

    public FramesCatalogDTO(Integer id, String name, Integer price, String status, Date dateAdded) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.status = status;
        this.dateAdded = dateAdded;
    }

    // Getters and Setters
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    // ToString for logging and debugging purposes
    @Override
    public String toString() {
        return "FramesCatalogDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", status='" + status + '\'' +
                ", dateAdded=" + dateAdded +
                '}';
    }
}

