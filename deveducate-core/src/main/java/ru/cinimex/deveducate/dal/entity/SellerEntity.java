package ru.cinimex.deveducate.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "sellers", schema = "vlutsenko")
public class SellerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sellers_seller_id_seq")
    @SequenceGenerator(name = "sellers_seller_id_seq", sequenceName = "sellers_seller_id_seq")
    @Column(name = "SELLER_ID", nullable = false, unique = true)
    private Integer sellerId;

    @Column(name = "SELLER_NAME")
    private String sellerName;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "CREATE_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createOn;

    @Column(name = "QUOTA")
    private Integer quota;

    @Column(name = "PRODUCT")
    private String product;

    @Column(name = "EXPIRES_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiresOn;

    @Column(name = "ADMIN_USER")
    private String adminUser;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "seller")
    @ToString.Exclude
    private List<OrderEntity> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {return false;}
        SellerEntity that = (SellerEntity) o;
        return sellerId != null && Objects.equals(sellerId, that.sellerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
