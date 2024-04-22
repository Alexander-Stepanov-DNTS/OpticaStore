package ru.stepanov.OpticaStore.DTO;

public class OrderFormDTO {

    private Integer id;
    private Integer frameId;
    private Integer lensId;
    private String descriptionOfRequiredWorks;
    private Integer clientReceptionId;

    // Constructors
    public OrderFormDTO() {}

    public OrderFormDTO(Integer id, Integer frameId, Integer lensId, String descriptionOfRequiredWorks, Integer clientReceptionId) {
        this.id = id;
        this.frameId = frameId;
        this.lensId = lensId;
        this.descriptionOfRequiredWorks = descriptionOfRequiredWorks;
        this.clientReceptionId = clientReceptionId;
    }

    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getFrameId() {
        return frameId;
    }

    public void setFrameId(Integer frameId) {
        this.frameId = frameId;
    }

    public Integer getLensId() {
        return lensId;
    }

    public void setLensId(Integer lensId) {
        this.lensId = lensId;
    }

    public String getDescriptionOfRequiredWorks() {
        return descriptionOfRequiredWorks;
    }

    public void setDescriptionOfRequiredWorks(String descriptionOfRequiredWorks) {
        this.descriptionOfRequiredWorks = descriptionOfRequiredWorks;
    }

    public Integer getClientReceptionId() {
        return clientReceptionId;
    }

    public void setClientReceptionId(Integer clientReceptionId) {
        this.clientReceptionId = clientReceptionId;
    }

    // toString method for logging and debugging purposes
    @Override
    public String toString() {
        return "OrderFormDTO{" +
                "id=" + id +
                ", frameId=" + frameId +
                ", lensId=" + lensId +
                ", descriptionOfRequiredWorks='" + descriptionOfRequiredWorks + '\'' +
                ", clientReceptionId=" + clientReceptionId +
                '}';
    }
}
