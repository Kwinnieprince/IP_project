package be.ucll.menu.menuService.model;

import be.ucll.menu.db.DishRepository;
import be.ucll.menu.db.MenuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;

@Service
public class MenuService {
    @Autowired
    DishRepository dishRepository;
    @Autowired
    MenuRepository menuRepository;

    public MenuService(){
//        dishes.add(new Dish("Balletjes", 2.5, Type.Dagschotel, nextId.incrementAndGet()));
//        dishes.add(new Dish("Soep", 1.5, Type.Soep, nextId.incrementAndGet()));
//        dishes.add(new Dish("Kip", 4, Type.Dagschotel, nextId.incrementAndGet()));
//        dishes.add(new Dish("Rijst", 2.5, Type.Veggie, nextId.incrementAndGet()));
    }

    public List<Dish> getDishes() {
        return dishRepository.findAll();
    }


    public Dish findDishByName(String name) {
        return dishRepository.findByName(name);
    }

    public Dish findDish(Dish dish){
        List<Dish> dishes = dishRepository.findAll();
        for(Dish d : dishes){
            if (d.equals(dish)){
                dish = d;
                break;
            }
        }
        return dish;
    }

    public void deleteDishByName(String name){
        dishRepository.deleteDishByName(name);
    }

    public Dish findDishById(int id) {
        return dishRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void addDish(Dish dish){
        if (!dishRepository.existsDishByName(dish.getName())){
            dishRepository.save(dish);
        }
    }

    public void updateDish(Dish dish){
        addDishToDb(dish);
    }

    private Dish addDishToDb(Dish dish){
        dishRepository.findById(dish.getId()).ifPresent(dishToAdd -> dish.setId(dishToAdd.getId()));
        return dishRepository.save(dish);
    }

    public void deleteDish(Dish dish){
        findDayMenuInDb(dish);
    }

    private void findDayMenuInDb(Dish dish) {
        if (menuRepository.findDayMenuByDagschotel(dish)){
            DayMenu menu = menuRepository.getDayMenuByDagschotel(dish);
            menuRepository.delete(menu);
            dishRepository.delete(dish);
        }else if (menuRepository.findDayMenuBySoup(dish)){
            DayMenu menu = menuRepository.getDayMenuBySoup(dish);
            dishRepository.delete(dish);
            menuRepository.delete(menu);
        }else if (menuRepository.findDayMenuByVeggie(dish)){
            DayMenu menu = menuRepository.getDayMenuByVeggie(dish);
            menuRepository.delete(menu);
            dishRepository.delete(dish);
        }else{
            dishRepository.delete(dish);
        }

    }

    public List<DayMenu> getWeekMenu(){
        List<DayMenu> weekmenu = menuRepository.findAll();
        LocalDate today = LocalDate.now();
        if (LocalDate.now().getDayOfWeek() != DayOfWeek.MONDAY){
            today = LocalDate.now(ZoneId.of("Europe/Brussels")).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }
        List<DayMenu> menu = new ArrayList<>();

        for (int i = 0; i < 7 ; i++){
            try {
//                DayMenu meal = weekmenu.get(i);
//                menu.add(meal);
                DayMenu meal = menuRepository.findById(today).orElseThrow(IllegalArgumentException::new);
                menu.add(meal);
//                if (meal.getDate().getDayOfWeek().equals(DayOfWeek.MONDAY) && meal.getDate().equals(today) && i == 0){
//                    menu.add(meal);
//                }else if (meal.getDate().equals(today)){
//                    menu.add(meal);
//                }
            }catch (Exception e){
                //
            }
            today = today.plusDays(1);
        }
        return menu;
    }

    public DayMenu addDayMenu(DayMenu dayMenu) {
        addDish(dayMenu.getSoup());
        addDish(dayMenu.getDagschotel());
        addDish(dayMenu.getVeggie());
        return menuRepository.save(dayMenu);
//        menus.put(dayMenu.getDate(), dayMenu);
    }

    public DayMenu findDayMenu(LocalDate date){
        return menuRepository.getOne(date);
//        return menuRepository.getOne(1);
//        return menus.get(date);
    }

    public List<DayMenu> changeDayMenu(LocalDate date, DayMenu dayMenu){
        //date = date.parse("dd-MM-yyyy");
        //DayMenu menu = menuRepository.getOne(date);
        dayMenu.setDate(date);

        DayMenu previousMenu = menuRepository.findById(dayMenu.getDate()).orElseThrow(IllegalArgumentException::new);
        previousMenu.getVeggie().setPrice(dayMenu.getVeggie().getPrice());
        previousMenu.getVeggie().setType(dayMenu.getVeggie().getType());

        previousMenu.getDagschotel().setPrice(dayMenu.getDagschotel().getPrice());
        previousMenu.getDagschotel().setType(dayMenu.getDagschotel().getType());

        previousMenu.getSoup().setPrice(dayMenu.getSoup().getPrice());
        previousMenu.getSoup().setType(dayMenu.getSoup().getType());
//        DayMenu menu = menuRepository.getOne(1);
//        System.out.println("Dag: " + menu.getDayOfWeek());
        commitToDatabase(previousMenu);
//        menu.setDishes(dayMenu.getSoup(), dayMenu.getDagschotel(), dayMenu.getVeggie());
//        menuRepository.save(menu);
        return getWeekMenu();
    }

    public void commitToDatabase(DayMenu dayMenu){
        addDayMenuToDb(dayMenu.getDagschotel());
        addDayMenuToDb(dayMenu.getSoup());
        addDayMenuToDb(dayMenu.getVeggie());
        menuRepository.save(dayMenu);
    }

    public Dish addDayMenuToDb(Dish dish){
        Dish dishToAdd = dishRepository.findByName(dish.getName());
        if(dishToAdd != null){
            dish.setId(dishToAdd.getId());
        }
//        dishRepository.findById(dish.getId()).ifPresent(dishToAdd -> dish.setId(dishToAdd.getId()));
        return dishRepository.save(dish);
    }

    public void deleteDayMenu(LocalDate date) {
        DayMenu menu = menuRepository.getOne(date);
        menuRepository.delete(menu);
    }

    public void deleteWeekMenu(LocalDate date){
        LocalDate today = date;
        if (LocalDate.now().getDayOfWeek() != DayOfWeek.MONDAY){
            today = LocalDate.now(ZoneId.of("Europe/Brussels")).with(TemporalAdjusters.previous(DayOfWeek.MONDAY));
        }
        for (int i = 0; i < 7 ; i++){
            try {
//                DayMenu meal = weekmenu.get(i);
//                menu.add(meal);
                DayMenu meal = menuRepository.findById(today).orElseThrow(IllegalArgumentException::new);
                menuRepository.delete(meal);
//                if (meal.getDate().getDayOfWeek().equals(DayOfWeek.MONDAY) && meal.getDate().equals(today) && i == 0){
//                    menu.add(meal);
//                }else if (meal.getDate().equals(today)){
//                    menu.add(meal);
//                }
            }catch (Exception e){
                //
            }
            today = today.plusDays(1);
        }
    }
}
