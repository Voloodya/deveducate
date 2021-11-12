package ru.cinimex.deveducate.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "orders", schema = "vlutsenko")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ORDER_ID", nullable = false)
    private Integer orderId;

    @Column(name = "ORDER_TIMESTAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date orderTimestam;

    @Column(name = "ORDER_TOTAL", nullable = false)
    private Integer orderTotal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SELLER_ID")
    private SellerEntity seller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CUSTOMER_ID")
    private CustomerEntity customer;

}
