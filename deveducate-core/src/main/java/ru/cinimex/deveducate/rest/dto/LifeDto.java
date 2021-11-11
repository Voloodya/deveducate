package ru.cinimex.deveducate.rest.dto;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class LifeDto {

    private boolean alive;
    private Date date;

    @Override
    public boolean equals(Object obj){

        if(obj == null || obj.getClass() != this.getClass()) return false;

        LifeDto life = (LifeDto) obj;

        if(this.alive == life.alive && this.getDate() == life.getDate()) return  true;
        else return false;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.alive ? "true".hashCode() : "false".hashCode());
        result = prime * result + ((this.date != null) ? this.date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Life{" +
                "Alive=" + alive +
                ", Date=" + date +
                '}';
    }
}
