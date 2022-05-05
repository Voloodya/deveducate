package ru.cinimex.deveducate.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
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
