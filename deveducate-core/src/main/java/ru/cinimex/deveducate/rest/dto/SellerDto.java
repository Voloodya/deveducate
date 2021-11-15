package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import java.util.Date;
import java.util.Set;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class SellerDto {

    private Integer sellerId;

    private String sellerName;

    private String password;

    private Date createOn;

    private Integer quota;

    private String product;

    private Date expiresOn;

    private String adminUser;

    private Set<OrderDto> orders;

}
