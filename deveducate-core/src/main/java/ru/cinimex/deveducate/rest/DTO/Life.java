package ru.cinimex.deveducate.rest.DTO;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class Life {

    private boolean Alive;
    private Date Date;

    @Override
    public boolean equals(Object obj){

        if(obj == null || obj.getClass() != this.getClass()) return false;

        Life life = (Life) obj;

        if(this.Alive == life.Alive && this.getDate() == life.getDate()) return  true;
        else return false;
    }

    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.Alive ? "true".hashCode() : "false".hashCode());
        result = prime * result + ((this.Date != null) ? this.Date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Life{" +
                "Alive=" + Alive +
                ", Date=" + Date +
                '}';
    }
}
