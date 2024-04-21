package ru.stepanov.OpticaStore.DTO;

import java.util.Date;

public class ContractDTO {

    private Integer id;
    private Integer clientId;  // Используем ID вместо целого объекта Client для упрощения
    private Integer orderFormId;  // Аналогично, ID вместо объекта OrderForm
    private Date contractConclusionDate;

    // Конструкторы
    public ContractDTO() {}

    public ContractDTO(Integer id, Integer clientId, Integer orderFormId, Date contractConclusionDate) {
        this.id = id;
        this.clientId = clientId;
        this.orderFormId = orderFormId;
        this.contractConclusionDate = contractConclusionDate;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getOrderFormId() {
        return orderFormId;
    }

    public void setOrderFormId(Integer orderFormId) {
        this.orderFormId = orderFormId;
    }

    public Date getContractConclusionDate() {
        return contractConclusionDate;
    }

    public void setContractConclusionDate(Date contractConclusionDate) {
        this.contractConclusionDate = contractConclusionDate;
    }

    @Override
    public String toString() {
        return "ContractDTO{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", orderFormId=" + orderFormId +
                ", contractConclusionDate=" + contractConclusionDate +
                '}';
    }
}
