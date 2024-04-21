package ru.stepanov.OpticaStore.models;
import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orderform")
//@Table(name = "OrderForm", uniqueConstraints = {@UniqueConstraint(columnNames = {"idFrame", "idLens", "date"})})
public class OrderForm {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "idframe", referencedColumnName = "id")
    private FramesCatalog frame;

    @ManyToOne
    @JoinColumn(name = "idlens", referencedColumnName = "id")
    private LensesCatalog lens;

    @Column(name = "descriptionofrequiredworks", length = 20)
    private String descriptionOfRequiredWorks;

    @OneToOne
    @JoinColumn(name = "idclientreception", referencedColumnName = "id")
    private ClientReception clientReception;

    public OrderForm() {
        // Пустой конструктор
    }

    public OrderForm(FramesCatalog frame, LensesCatalog lens, String descriptionOfRequiredWorks, ClientReception clientReception) {
        this.frame = frame;
        this.lens = lens;
        this.descriptionOfRequiredWorks = descriptionOfRequiredWorks;
        this.clientReception = clientReception;
    }

    // Геттеры и сеттеры

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public FramesCatalog getFrame() {
        return frame;
    }

    public void setFrame(FramesCatalog frame) {
        this.frame = frame;
    }

    public LensesCatalog getLens() {
        return lens;
    }

    public void setLens(LensesCatalog lens) {
        this.lens = lens;
    }

    public String getDescriptionOfRequiredWorks() {
        return descriptionOfRequiredWorks;
    }

    public void setDescriptionOfRequiredWorks(String descriptionOfRequiredWorks) {
        this.descriptionOfRequiredWorks = descriptionOfRequiredWorks;
    }

    public ClientReception getClientReception() {
        return clientReception;
    }

    public void setClientReception(ClientReception clientReception) {
        this.clientReception = clientReception;
    }

    @Override
    public String toString() {
        return "OrderForm{" +
                "id=" + id +
                ", idFrame=" + frame +
                ", idLens=" + lens +
                '}';
    }
}

