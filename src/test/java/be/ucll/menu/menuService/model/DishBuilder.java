package be.ucll.menu.menuService.model;

public class DishBuilder {

    private String name;
    private double price;
    private Type type;

    private DishBuilder(){}

    public static DishBuilder aDish(){
        return new DishBuilder();
    }

    public static DishBuilder aOkDish(){
        return new DishBuilder()
                .withName("Erwten")
                .withPrice(2.5)
                .withType(Type.Veggie);
    }

    public DishBuilder withName(String name){
        this.name = name;
        return this;
    }

    public DishBuilder withPrice(double price){
        this.price = price;
        return this;
    }

    public DishBuilder withType(Type type){
        this.type = type;
        return this;
    }

    public Dish build(){
        Dish dish = new Dish();

        dish.setName(this.name);
        dish.setPrice(this.price);
        dish.setType(this.type);

        return dish;
    }
}
