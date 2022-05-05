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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customers_customer_id_seq")
    @SequenceGenerator(name = "customers_customer_id_seq", sequenceName = "customers_customer_id_seq")
    @Column(name = "CUSTOMER_ID", nullable = false, unique = true)
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
        if (this == o) {
            return true;
        }
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        CustomerEntity that = (CustomerEntity) o;
        return customerId != null && Objects.equals(customerId, that.customerId);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
