package ru.cinimex.deveducate.rest.dto;

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
public class SellerDto {

    private Integer id;

    private String name;

    private String password;

    private Date createOn;

    private Integer quota;

    private String product;

    private Date updateOn;

    private String adminUser;

}
