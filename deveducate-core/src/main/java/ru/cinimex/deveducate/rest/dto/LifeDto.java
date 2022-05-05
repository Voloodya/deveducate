package ru.cinimex.deveducate.rest.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LifeDto {

    private boolean alive;
    private Date date;

    @Override
    public String toString() {
        return "Life{" +
                "Alive=" + alive +
                ", Date=" + date +
                '}';
    }
}
