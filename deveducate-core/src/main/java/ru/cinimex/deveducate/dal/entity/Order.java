package ru.cinimex.deveducate.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
@Entity
@Table(name = "Orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ORDER_ID", nullable = false)
    private Integer OrderId;

    @Column(name = "ORDER_TIMESTAM")
    @Temporal(TemporalType.TIMESTAMP)
    private Date OrderTimestam;

    @Column(name = "ORDER_TOTAL", nullable = false)
    private Integer OrderTotal;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SELLER_ID")
    private Seller seller;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer Customer;

    public Order() {
    }
}
