package ru.cinimex.deveducate.dal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "vlutsenko")
public class OrderEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;

    @Column(name = "ORDER_TIMESTAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTimestam;

    @Column(name = "ORDER_TOTAL")
    private Integer orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID")
    @ToString.Exclude
    private SellerEntity seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        OrderEntity that = (OrderEntity) o;
        return orderId != null && Objects.equals(orderId, that.orderId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
