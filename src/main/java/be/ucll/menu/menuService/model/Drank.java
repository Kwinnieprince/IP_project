package be.ucll.menu.menuService.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;

@Table(name = "drank")
@Entity
public class Drank {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    @NotNull
    @NotEmpty
    @Size(min=4, max=80)
    private String name;

    @NotNull
    @DecimalMin("0.5")
    @DecimalMax("10.00")
    private double price;

    private DrankType type;

    public Drank(){}

    @JsonCreator
    public Drank(@JsonProperty("beschrijvingDrank") String name, @JsonProperty("prijsDrank") double price, @JsonProperty("typeDrank") DrankType type){
        setType(type);
        setName(name);
        setPrice(price);
    }

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(DrankType type) {
        this.type = type;
    }

    public void setPrice(double price){
        this.price = price;
    }

    @JsonProperty("beschrijvingDrank")
    public String getNameDrank() {
        return name;
    }

    @JsonProperty("prijsDrank")
    public double getPriceDrank() {
        return price;
    }

    @JsonProperty("typeDrank")
    public DrankType getTypeDrank(){
        return this.type;
    }
}
