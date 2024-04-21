package ru.stepanov.OpticaStore.models;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "lastname")
    private String lastName;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "middlename")
    private String middleName;

    @Column(name = "gender")
    private String gender;

    @Column(name = "phone")
    private Long phone;

    @Column(name = "birthdate")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date birthDate;

    @OneToOne(mappedBy = "client", fetch = FetchType.LAZY)
    //@Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Document document;

    @OneToMany(mappedBy = "client")
    private List<Contract> contracts;

    @OneToMany(mappedBy = "client", fetch = FetchType.LAZY)
    private List<ClientReception> clientReceptions;

    public Client() {
    }

    public Client(String lastName, String firstName, String middleName, String gender, Long phone, Date birthDate) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClient() {
        return id;
    }

    public void setIdClient(Integer idClient) {
        this.id = idClient;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Long getPhone() {
        return phone;
    }

    public void setPhone(Long phone) {
        this.phone = phone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public List<Contract> getContracts() {
        return contracts;
    }

    public void setContracts(List<Contract> contracts) {
        this.contracts = contracts;
    }


    public List<ClientReception> getClientReceptions() {
        return clientReceptions;
    }

    public void setClientReceptions(List<ClientReception> clientReceptions) {
        this.clientReceptions = clientReceptions;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                ", birthDate=" + birthDate +
                '}';
    }
}

enum Gender {
    M, W
}
