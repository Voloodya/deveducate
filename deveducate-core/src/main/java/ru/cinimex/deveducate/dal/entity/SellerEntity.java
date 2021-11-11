package ru.cinimex.deveducate.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sellers", schema = "vlutsenko")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "SELLER_ID", nullable = false)
    private Integer sellerId;

    @Column(name = "SELLER_NAME", nullable = true)
    private String sellerName;

    @Column(name = "PASSWORD", nullable = true)
    private String password;

    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "QUOTA", nullable = true)
    private Integer quota;

    @Column(name = "PRODUCT", nullable = true)
    private String product;

    @Column(name = "EXPIRES_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresOn;

    @Column(name = "ADMIN_USER", nullable = true)
    private String adminUser;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "seller")
    private Set<OrderEntity> orders;

}
