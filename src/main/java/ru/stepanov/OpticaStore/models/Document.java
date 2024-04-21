package ru.stepanov.OpticaStore.models;

import jakarta.persistence.*;

import java.io.Serializable;

@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;  // Уникальный идентификатор для каждого документа

    @OneToOne()
    @JoinColumn(name = "client_id", referencedColumnName = "id")
    private Client client;  // Связь с таблицей клиентов

    @Column(name = "doc_type")
    private String docType;

    @Column(name = "series")
    private Integer series;

    @Column(name = "number")
    private Integer number;


    public Document() {}

    public Document(Client Client, String docType, Integer series, Integer number) {
        this.client = Client;
        this.docType = docType;
        this.series = series;
        this.number = number;
    }

    public Client getClient() {
        return client;
    }


    public void setClient(Client client) {
        this.client = client;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String documentType) {
        this.docType = documentType;
    }

    public Integer getSeries() {
        return series;
    }

    public void setSeries(Integer series) {
        this.series = series;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Document{" +
                "idClient=" + client +
                ", documentType=" + docType +
                ", series=" + series +
                ", number=" + number +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

}


