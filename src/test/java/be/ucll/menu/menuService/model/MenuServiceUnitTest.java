package be.ucll.menu.menuService.model;

import be.ucll.menu.db.DishRepository;
import be.ucll.menu.db.MenuRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
public class MenuServiceUnitTest {
    @TestConfiguration
    public static class getMenuServiceTestContextConfiguration{
        @Bean
        public MenuService MenuService(){
            return new MenuService();
        }
    }

    @Autowired
    MenuService menuService;

    @MockBean
    private DishRepository dishRepository;

    @MockBean
    private MenuRepository menuRepository;

    private Dish soup, dagschotel, veggie;
    private DayMenu dayMenu;
    private DayMenu changhedDayMenu;
    private List<DayMenu> dayMenus;
    private List<Dish> dishes;

    @Before
    public void setUp(){
        dayMenus = new ArrayList<>();
        dishes = new ArrayList<>();

        soup = new Dish("Soep", 1.5, Type.Soep);
        dagschotel = new Dish("Kip", 4, Type.Dagschotel);
        veggie = new Dish("Rijst", 2.5, Type.Veggie);

        dishes.add(soup);
        dishes.add(dagschotel);
        dishes.add(veggie);

        String date = "28-04-2019";
        dayMenu = new DayMenu(soup, dagschotel, veggie, date);

        soup = new Dish("Soep", 2.5, Type.Soep);
        changhedDayMenu = new DayMenu(soup, dagschotel, veggie, date);

        dayMenus.add(dayMenu);

    }

    @Test
    public void gives_all_dishes(){
        Mockito.when(dishRepository.findAll()).thenReturn(dishes);

        List<Dish> returnedDishes = menuService.getDishes();

        assertThat(returnedDishes.size()).isEqualTo(3);
    }

//    @Test
//    public void find_dish_by_dish(){
//
//        Dish dishToFind = new Dish();
//
//        Mockito.when(dishRepository.findAll()).thenReturn(dishes);
//
//        List<Dish> returnedDishes = menuService.getDishes();
//
//        for (Dish dish : returnedDishes){
//            if (dish.equals(soup)){
//                dishToFind = soup;
//            }
//        }
//
//        assertThat(dishToFind).isEqualTo(soup);
//    }

    @Test
    public void delete_dish_by_name_deletes_dish(){
        Mockito.when(dishRepository.findAll()).thenReturn(dishes);
        dishRepository.deleteDishByName(soup.getName());


        List<Dish> returnedDishes = dishRepository.findAll();

        assertThat(returnedDishes.size()).isEqualTo(3);

    }

    @Test
    public void add_dish(){
        Mockito.when(dishRepository.save(dagschotel)).thenReturn(dagschotel);

        Dish returnedDish = dishRepository.save(dagschotel);

        assertThat(returnedDish.getId()).isEqualTo(dagschotel.getId());
        assertThat(returnedDish.getName()).isEqualTo(dagschotel.getName());
        assertThat(returnedDish.getPrice()).isEqualTo(dagschotel.getPrice());
        assertThat(returnedDish.getType()).isEqualTo(dagschotel.getType());
    }

    @Test
    public void find_dish_by_name(){
        Mockito.when(dishRepository.findByName(soup.getName())).thenReturn(soup);

        Dish returnedDish = dishRepository.findByName(soup.getName());
        assertThat(returnedDish.getId()).isEqualTo(soup.getId());
        assertThat(returnedDish.getName()).isEqualTo(soup.getName());
        assertThat(returnedDish.getPrice()).isEqualTo(soup.getPrice());
        assertThat(returnedDish.getType()).isEqualTo(soup.getType());
    }

//    @Test
//    public void findDishById() {
//        Mockito.when(dishRepository.findById(soup.getId()).orElseThrow(IllegalArgumentException::new)).thenReturn(soup);
//
//        Dish returnedDish = dishRepository.findById(soup.getId()).orElseThrow(IllegalArgumentException::new);
//
//        assertThat(returnedDish).isEqualTo(soup);
//    }

    @Test
    public void addDish() {
        Mockito.when(dishRepository.save(dagschotel)).thenReturn(dagschotel);

        Dish returnedDish = dishRepository.save(dagschotel);

        assertThat(returnedDish).isEqualTo(dagschotel);
    }

//    @Test
//    public void updateDish() {
//        Mockito.when(dishRepository.findById(soup.getId()).ifPresent(dishToAdd -> soup.setId(dishToAdd.getId()))).thenReturn(soup);
//
//
//
//    }


//    @Test
//    public void deleteDish() {
//
//    }

    @Test
    public void add_day_menu(){
        Mockito.when(menuRepository.save(dayMenu)).thenReturn(dayMenu);

        DayMenu returnedDayMenu = menuRepository.save(dayMenu);

        assertThat(returnedDayMenu.getDagschotel()).isEqualTo(dayMenu.getDagschotel());
        assertThat(returnedDayMenu.getSoup()).isEqualTo(dayMenu.getSoup());
        assertThat(returnedDayMenu.getVeggie()).isEqualTo(dayMenu.getVeggie());
    }

    @Test
    public void findDayMenu() {
        Mockito.when(menuRepository.getOne(dayMenu.getDate())).thenReturn(dayMenu);

        DayMenu returnedDayMenu = menuRepository.getOne(dayMenu.getDate());

        assertThat(returnedDayMenu).isEqualTo(dayMenu);
    }

}