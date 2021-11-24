package ru.cinimex.deveducate.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {

    private Integer id;

    private Date orderTimestamp;

    private Integer orderTotal;

    private SellerDto seller;

    private CustomerDto customer;

}
