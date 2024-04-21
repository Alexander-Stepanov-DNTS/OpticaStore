package ru.stepanov.OpticaStore.models;
import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Table(name = "contracts")
public class Contract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName = "id", nullable = false)
    private Client client;

    @ManyToOne
    @JoinColumn(name = "idorderform")
    private OrderForm orderForm;

    @Column(name = "contractconclusiondate", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date contractConclusionDate;

    public Contract() {
        // Пустой конструктор
    }

    public Contract(Client client, OrderForm orderForm, Date contractConclusionDate) {
        this.client = client;
        this.orderForm = orderForm;
        this.contractConclusionDate = contractConclusionDate;
    }

    // Геттеры и сеттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public Date getContractConclusionDate() {
        return contractConclusionDate;
    }

    public void setContractConclusionDate(Date contractConclusionDate) {
        this.contractConclusionDate = contractConclusionDate;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "id=" + id +
                ", idClient=" + client.getIdClient() +
                ", idOrderForm=" + orderForm.getId() +
                ", contractConclusionDate=" + contractConclusionDate +
                '}';
    }
}

