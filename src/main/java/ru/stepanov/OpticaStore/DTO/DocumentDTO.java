package ru.stepanov.OpticaStore.DTO;

public class DocumentDTO {

    private Integer id;
    private Integer clientId;  // ID клиента для упрощения передачи данных
    private String docType;
    private Integer series;
    private Integer number;

    // Конструкторы
    public DocumentDTO() {}

    public DocumentDTO(Integer id, Integer clientId, String docType, Integer series, Integer number) {
        this.id = id;
        this.clientId = clientId;
        this.docType = docType;
        this.series = series;
        this.number = number;
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

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
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
        return "DocumentDTO{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", docType='" + docType + '\'' +
                ", series=" + series +
                ", number=" + number +
                '}';
    }
}

