package ru.stepanov.OpticaStore.DTO;

import java.util.Date;

public class ClientReceptionDTO {

    private Integer id;
    private Date dateAdded;
    private String leftEyeExaminationResults;
    private String rightEyeExaminationResults;
    private String prescriptionForRightLens;
    private String prescriptionForLeftLens;
    private Integer clientId;  // ID клиента вместо объекта Client для упрощения передачи
    private Integer orderFormId;  // ID связанной формы заказа, если она существует

    // Конструкторы
    public ClientReceptionDTO() {}

    public ClientReceptionDTO(Integer id, Date dateAdded, String leftEyeExaminationResults,
                              String rightEyeExaminationResults, String prescriptionForRightLens,
                              String prescriptionForLeftLens, Integer clientId, Integer orderFormId) {
        this.id = id;
        this.dateAdded = dateAdded;
        this.leftEyeExaminationResults = leftEyeExaminationResults;
        this.rightEyeExaminationResults = rightEyeExaminationResults;
        this.prescriptionForRightLens = prescriptionForRightLens;
        this.prescriptionForLeftLens = prescriptionForLeftLens;
        this.clientId = clientId;
        this.orderFormId = orderFormId;
    }

    // Геттеры и сеттеры
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        this.dateAdded = dateAdded;
    }

    public String getLeftEyeExaminationResults() {
        return leftEyeExaminationResults;
    }

    public void setLeftEyeExaminationResults(String leftEyeExaminationResults) {
        this.leftEyeExaminationResults = leftEyeExaminationResults;
    }

    public String getRightEyeExaminationResults() {
        return rightEyeExaminationResults;
    }

    public void setRightEyeExaminationResults(String rightEyeExaminationResults) {
        this.rightEyeExaminationResults = rightEyeExaminationResults;
    }

    public String getPrescriptionForRightLens() {
        return prescriptionForRightLens;
    }

    public void setPrescriptionForRightLens(String prescriptionForRightLens) {
        this.prescriptionForRightLens = prescriptionForRightLens;
    }

    public String getPrescriptionForLeftLens() {
        return prescriptionForLeftLens;
    }

    public void setPrescriptionForLeftLens(String prescriptionForLeftLens) {
        this.prescriptionForLeftLens = prescriptionForLeftLens;
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
}
