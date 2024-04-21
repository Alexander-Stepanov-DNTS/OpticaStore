package ru.stepanov.OpticaStore.DTO;

import java.util.Date;

public class ClientDTO {
    private Integer id;
    private String lastName;
    private String firstName;
    private String middleName;
    private String gender;
    private Long phone;
    private Date birthDate;

    // Конструкторы, геттеры и сеттеры
    public ClientDTO() {}

    public ClientDTO(Integer id, String lastName, String firstName, String middleName,
                     String gender, Long phone, Date birthDate) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
        this.gender = gender;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
