package ru.cinimex.deveducate.rest.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SellerDto {

    private Integer SellerId;

    private  String SellerName;

    private  String Password;

    private Date CreateOn;

    private Integer Quota;

    private  String Product;

    private Date ExpiresOn;

    private  String AdminUser;

    private Set<OrderDto> orders;

}
