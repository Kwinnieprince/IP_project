package be.ucll.menu.menuService.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.*;


@Table(name = "dish")
@Entity
public class Dish {

    @NotNull
    @NotEmpty
    @Size(min=4, max=50)
    private String name;

    @NotNull
    @DecimalMin("0.1")
    @DecimalMax("10.00")
    private double price;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO )
    private int id;

    private Type type;

    @JsonCreator
    public Dish(@JsonProperty("beschrijving") String name, @JsonProperty("prijs") double price, @JsonProperty("type") Type type){
        setType(type);
        setName(name);
        setPrice(price);
    }

    public Dish(){}

    public void setId(int id){
        this.id = id;
    }

    @JsonIgnore
    public int getId(){
        return id;
    }

    @JsonProperty("beschrijving")
    public String getName() {
        return name;
    }

    @JsonProperty("prijs")
    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setType(Type type){
        this.type = type;
    }

    @JsonProperty("type")
    public Type getType(){
        return this.type;
    }
}
