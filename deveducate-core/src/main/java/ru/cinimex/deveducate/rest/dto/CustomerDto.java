package ru.cinimex.deveducate.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDto {

    private Integer customerId;

    private String custFirstName;

    private String  custLastName;

    private String  phoneNumber1;

    private String  phoneNumber2;

    private String  custStreetADddress1;

    private String  custStreetADddress2;

    private String  custSity;

    private String  custPostalCode;

    private Integer  creditLimit;

    private String  custEmail;

    private Set<OrderDto> orders;

}
