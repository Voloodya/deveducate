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
@Table(name = "Customers", schema = "vlutsenko")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CUSTOMER_ID", nullable = false)
    private Integer CustomerId;

    @Column(name = "CUST_FIRST_NAME", nullable = false, unique = false)
    private String CustFirstName;

    @Column(name = "CUST_LAST_NAME", nullable = false, unique = false)
    private String  CustLastName;

    @Column(name = "PHONE_NUMBER1", nullable = false, unique = false)
    private String  PhoneNumber1;

    @Column(name = "PHONE_NUMBER2", nullable = true, unique = false)
    private String  PhoneNumber2;

    @Column(name = "CUST_STREET_ADDRESS1", nullable = false, unique = false)
    private String  CustStreetADddress1;

    @Column(name = "CUST_STREET_ADDRESS2", nullable = true, unique = false)
    private String  CustStreetADddress2;

    @Column(name = "CUST_CITY", nullable = false, unique = false)
    private String  CustSity;

    @Column(name = "CUST_POSTAL_CODE", nullable = false, unique = false)
    private String  CustPostalCode;

    @Column(name = "CREDIT_LIMIT", nullable = false, unique = false)
    private Integer  CreditLimit;

    @Column(name = "CUST_EMAIL", nullable = true, unique = false)
    private String  CustEmail;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "Customer")
    private Set<Order> orders;


}
