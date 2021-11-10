package ru.cinimex.deveducate.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer OrderId;

    private Date OrderTimestam;

    private Integer OrderTotal;

    private SellerDto seller;

    private CustomerDto Customer;

}
