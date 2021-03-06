package be.ucll.menu.menuService.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Entity
@Table(name = "day_menu")
public class DayMenu {

    @Valid
    private DayOfWeek dayOfWeek;

//    @OneToOne(cascade = CascadeType.ALL)
//    @DateTimeFormat(pattern = "dd-MM-yyyy")

    @Id
    @Valid
    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate date;

    @ManyToOne (cascade = CascadeType.DETACH)
    @Valid
    private Dish soup;

    @ManyToOne (cascade = CascadeType.DETACH)
    @Valid
    private Dish dagschotel;

    @ManyToOne (cascade = CascadeType.DETACH)
    @Valid
    private Dish veggie;

    @ManyToOne (cascade = CascadeType.ALL)
    @Valid
    private Drank drank1;

    @ManyToOne (cascade = CascadeType.ALL)
    @Valid
    private Drank drank2;

    public DayMenu(){    }

    public DayMenu(@JsonProperty("soep") Dish soup, @JsonProperty("dagschotel") Dish dagschotel, @JsonProperty("veggie") Dish veggie, @JsonProperty("datum") String date, @JsonProperty("drank1")Drank drank1, @JsonProperty("drank2") Drank drank2){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("[yyyy-MM-dd][yyyy/MM/dd][dd-MM-yyyy][dd/MM/yyyy]");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);
        setDishes(soup, dagschotel, veggie);
        setDate(dateFromUrl);
        setDayOfWeek(dateFromUrl.getDayOfWeek());
        addDrinks(drank1, drank2);
    }

    public void setDishes(Dish soup, Dish dagschotel, Dish veggie){
        if (soup != null){
            this.soup = soup;
        }
        if (dagschotel != null){
            this.dagschotel = dagschotel;
        }
        if (veggie != null){
            this.veggie = veggie;
        }
    }

    public void setDate(LocalDate date){
        this.date = date;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    @JsonProperty("datum")
    public LocalDate getDate() {//LocalDate getDate() {
        return date;
    }

    @JsonIgnore
    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    @JsonProperty("dag")
    public String getDayOfWeekAsString() {
        switch (dayOfWeek){
            case MONDAY: return "Maandag";
            case TUESDAY: return "Dinsdag";
            case WEDNESDAY: return "Woensdag";
            case THURSDAY: return "Donderdag";
            case FRIDAY: return "Vrijdag";
            case SATURDAY: return "Zaterdag";
            case SUNDAY: return "Zondag";
            default: return "";
        }
    }

    public void addDrinks(Drank drank1, Drank drank2){
        this.drank1 = drank1;
        this.drank2 = drank2;
    }

    @JsonProperty("drank1")
    public Drank getDrank1(){
        return drank1;
    }

    @JsonProperty("drank2")
    public Drank getDrank2(){
        return drank2;
    }

    @JsonProperty("dagschotel")
    public Dish getDagschotel() {
        return dagschotel;
    }

    @JsonProperty("soep")
    public Dish getSoup() {
        return soup;
    }

    @JsonProperty("veggie")
    public Dish getVeggie() {
        return veggie;
    }
}
