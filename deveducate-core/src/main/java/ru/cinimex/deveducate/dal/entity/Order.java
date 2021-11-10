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
@Table(name = "Orders", schema = "vlutsenko")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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

}
