package ru.cinimex.deveducate.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Integer orderId;

    private Date orderTimestam;

    private Integer orderTotal;

    private SellerDto seller;

    private CustomerDto customer;

}
