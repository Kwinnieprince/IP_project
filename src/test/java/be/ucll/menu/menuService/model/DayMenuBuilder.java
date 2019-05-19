package be.ucll.menu.menuService.model;

import java.time.LocalDate;

public class DayMenuBuilder {
    Dish soup;
    Dish dagschotel;
    Dish veggie;
    LocalDate date;

    private DayMenuBuilder(){}


    public static DayMenuBuilder anDayMenu(){
        return new DayMenuBuilder();
    }

    public static DayMenuBuilder anOkDayMenu(LocalDate date){
        return new DayMenuBuilder()
                .withSoup(new Dish("Soep", 2.5, Type.Soep))
                .withDagschotel(new Dish("Frietjes", 4.3, Type.Dagschotel))
                .withVeggie(new Dish("Rijst", 4, Type.Veggie))
                .onDate(date);
    }

    public DayMenuBuilder withSoup(Dish soup){
        this.soup = soup;
        return this;
    }

    public DayMenuBuilder withDagschotel(Dish dagschotel){
        this.dagschotel = dagschotel;
        return this;
    }

    public DayMenuBuilder withVeggie(Dish veggie){
        this.veggie = veggie;
        return this;
    }

    public DayMenuBuilder onDate(LocalDate date){
        this.date = date;
        return this;
    }

    public DayMenu build(){
        DayMenu dayMenu = new DayMenu();

        dayMenu.setDate(date);
        dayMenu.setDishes(soup, dagschotel, veggie);

        return dayMenu;
    }
}
