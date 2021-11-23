package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder(toBuilder = true)
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
