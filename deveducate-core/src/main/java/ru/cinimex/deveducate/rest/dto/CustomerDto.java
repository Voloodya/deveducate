package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerDto {

    private Integer id;

    private String firstName;

    private String lastName;

    private String phone1;

    private String phone2;

    private String street1;

    private String street2;

    private String city;

    private String postal;

    private Integer creditLimit;

    private String email;

}
