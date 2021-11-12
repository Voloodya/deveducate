package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Integer orderId;

    private Date orderTimestam;

    private Integer orderTotal;

    private SellerDto seller;

    private CustomerDto customer;

}
