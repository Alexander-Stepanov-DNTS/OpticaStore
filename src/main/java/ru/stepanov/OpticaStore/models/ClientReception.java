package ru.stepanov.OpticaStore.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "clientreception")
public class ClientReception {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "dateadded", columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private Date dateAdded;

    @Column(name = "lefteyeexaminationresults", length = 20)
    private String leftEyeExaminationResults;

    @Column(name = "righteyeexaminationresults", length = 20)
    private String rightEyeExaminationResults;

    @Column(name = "prescriptionforrightlens", length = 20)
    private String prescriptionForRightLens;

    @Column(name = "prescriptionforleftlens", length = 20)
    private String prescriptionForLeftLens;

    @ManyToOne
    @JoinColumn(name = "idclient", referencedColumnName="id")
    private Client client;

    @OneToOne(mappedBy = "clientReception", fetch = FetchType.LAZY)
    private OrderForm orderForm;

    public ClientReception () {
        // Пустой конструктор
    }

    public ClientReception (Date dateAdded, String leftEyeExaminationResults, String rightEyeExaminationResults, String prescriptionForRightLens, String prescriptionForLeftLens) {
        this.dateAdded = dateAdded;
        this.leftEyeExaminationResults = leftEyeExaminationResults;
        this.rightEyeExaminationResults = rightEyeExaminationResults;
        this.prescriptionForRightLens = prescriptionForRightLens;
        this.prescriptionForLeftLens = prescriptionForLeftLens;
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

    public OrderForm getOrderForm() {
        return orderForm;
    }

    public void setOrderForm(OrderForm orderForm) {
        this.orderForm = orderForm;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}

