package ru.cinimex.deveducate.dal.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "customers", schema = "vlutsenko")
public class CustomerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer customerId;

    @Column(name = "CUST_FIRST_NAME")
    private String custFirstName;

    @Column(name = "CUST_LAST_NAME")
    private String custLastName;

    @Column(name = "PHONE_NUMBER1")
    private String phoneNumber1;

    @Column(name = "PHONE_NUMBER2")
    private String phoneNumber2;

    @Column(name = "CUST_STREET_ADDRESS1")
    private String custStreetADddress1;

    @Column(name = "CUST_STREET_ADDRESS2")
    private String custStreetADddress2;

    @Column(name = "CUST_CITY")
    private String custSity;

    @Column(name = "CUST_POSTAL_CODE")
    private String custPostalCode;

    @Column(name = "CREDIT_LIMIT")
    private Integer creditLimit;

    @Column(name = "CUST_EMAIL")
    private String custEmail;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "customer")
    private Set<OrderEntity> orders;


}
