package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class OrderDto {

    private Integer id;

    private Date orderTimestamp;

    private Integer orderTotal;

    private SellerDto seller;

    private CustomerDto customer;

}
