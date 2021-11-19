package ru.cinimex.deveducate.dal.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
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
    private String custStreetAddress1;

    @Column(name = "CUST_STREET_ADDRESS2")
    private String custStreetAddress2;

    @Column(name = "CUST_CITY")
    private String custCity;

    @Column(name = "CUST_POSTAL_CODE")
    private String custPostalCode;

    @Column(name = "CREDIT_LIMIT")
    private Integer creditLimit;

    @Column(name = "CUST_EMAIL")
    private String custEmail;

    @OneToMany(fetch = FetchType.LAZY, cascade = {CascadeType.REFRESH, CascadeType.MERGE}, mappedBy = "customer")
    @ToString.Exclude
    private List<OrderEntity> orders;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        CustomerEntity that = (CustomerEntity) o;
        return customerId != null && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
