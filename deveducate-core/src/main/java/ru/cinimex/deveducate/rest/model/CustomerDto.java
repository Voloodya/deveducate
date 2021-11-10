package ru.cinimex.deveducate.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Integer CustomerId;

    private String CustFirstName;

    private String  CustLastName;

    private String  PhoneNumber1;

    private String  PhoneNumber2;

    private String  CustStreetADddress1;

    private String  CustStreetADddress2;

    private String  CustSity;

    private String  CustPostalCode;

    private Integer  CreditLimit;

    private String  CustEmail;

    private Set<OrderDto> orders;

}
