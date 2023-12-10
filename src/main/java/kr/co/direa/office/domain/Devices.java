package kr.co.direa.office.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Entity(name = "devices")
@Table(name = "devices")
@Getter
public class Devices extends BaseTimeEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name ="user_id", referencedColumnName = "id")
    private Users userId;

    @ManyToOne
    @JoinColumn(name ="manage_dep", referencedColumnName = "id")
    private Departments manageDep;

    @ManyToOne
    @JoinColumn(name ="category_id", referencedColumnName = "id")
    private Categories categoryId;

    @Column(name = "spec")
    private String spec;

    @Column(name = "price")
    private Long price;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "tag")
    private String tag;

    @Column(name = "company")
    private String company;

    @Column(name = "sn")
    private String sn;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "purpose")
    private String purpose;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "purchase_date")
    private Date purchaseDate;

    public Devices() {

    }

    @Builder
    public Devices(Users userId, Departments manageDep, Categories categoryId, String spec,
                   Long price, String model, String description, String tag, String company,
                   String sn, Boolean status, String purpose, Date purchaseDate) {
        this.userId = userId;
        this.manageDep = manageDep;
        this.categoryId = categoryId;
        this.spec = spec;
        this.price = price;
        this.model = model;
        this.description = description;
        this.tag = tag;
        this.company = company;
        this.sn = sn;
        this.status = status;
        this.purpose = purpose;
        this.purchaseDate = purchaseDate;
    }

}
