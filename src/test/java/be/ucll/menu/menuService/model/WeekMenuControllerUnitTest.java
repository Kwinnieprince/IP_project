package be.ucll.menu.menuService.model;

import be.ucll.menu.controller.WeekMenuController;
import be.ucll.menu.db.DishRepository;
import be.ucll.menu.db.MenuRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(WeekMenuController.class)
public class WeekMenuControllerUnitTest {

    @Autowired
    private MockMvc weekMenuController;

    @MockBean
    private MenuService service;

    @MockBean
    private DishRepository dishRepository;

    @MockBean
    private MenuRepository menuRepository;

    @Test
    public void returns_week_menu_in_json_array_with_dishes_of_this_week() throws Exception {
        LocalDate today = LocalDate.now();
        Dish soup1 = new Dish("Tomatensoep", 2, Type.Soep);
        Dish soup2 = new Dish("Spruitensoep", 2.5, Type.Soep);
        Dish soup3 = new Dish("Vissoep", 2, Type.Soep);

        Dish dagschotel1 = new Dish("Frietjes", 4.6, Type.Dagschotel);
        Dish dagschotel2 = new Dish("Balletjes", 4.6, Type.Dagschotel);
        Dish dagschotel3 = new Dish("Kip met aardappelen", 4.6, Type.Dagschotel);

        Dish veggie1 = new Dish("Wortelen", 4, Type.Veggie);
        Dish veggie2 = new Dish("Ajuin", 4, Type.Veggie);
        Dish veggie3 = new Dish("Rijst", 4, Type.Veggie);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        DayMenu dayMenu1 = new DayMenu(soup1, dagschotel1, veggie1, today.format(formatter));
        DayMenu dayMenu2 = new DayMenu(soup2, dagschotel2, veggie2, today.format(formatter));
        DayMenu dayMenu3 = new DayMenu(soup3, dagschotel3, veggie3, today.format(formatter));

        menuRepository.save(dayMenu1);
        menuRepository.save(dayMenu2);
        menuRepository.save(dayMenu3);

        List<DayMenu> weekmenu = new ArrayList<>();
        weekmenu.add(dayMenu1);
        weekmenu.add(dayMenu2);
        weekmenu.add(dayMenu3);

        Mockito.when(service.getWeekMenu()).thenReturn(Arrays.asList(dayMenu1, dayMenu2, dayMenu3));

        weekMenuController.perform(get("/weekmenu").contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(3)));

    }
}
