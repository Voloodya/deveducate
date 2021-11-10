package ru.cinimex.deveducate.dal.entity;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "Sellers")
public class Seller {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SELLER_ID", nullable = false)
    private Integer SellerId;

    @Column(name = "SELLER_NAME", nullable = false)
    private  String SellerName;

    @Column(name = "PASSWORD", nullable = false)
    private  String Password;

    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date CreateOn;

    @Column(name = "QUOTA", nullable = false)
    private Integer Quota;

    @Column(name = "PRODUCT", nullable = false)
    private  String Product;

    @Column(name = "EXPIRES_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ExpiresOn;

    @Column(name = "ADMIN_USER", nullable = false)
    private  String AdminUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")
    private Set<Order> orders;

}
