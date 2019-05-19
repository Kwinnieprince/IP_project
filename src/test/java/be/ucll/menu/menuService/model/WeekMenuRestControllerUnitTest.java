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
import java.util.Arrays;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(SpringRunner.class)
@WebMvcTest(WeekMenuController.class)
public class WeekMenuRestControllerUnitTest {
    @Autowired
    private MockMvc weekMenuController;

    @MockBean
    private MenuService menuService;

    @MockBean
    private DishRepository dishRepository;

    @MockBean
    private MenuRepository menuRepository;

    @Test
    public void given_weekmenu_when_get_weekmenu_return_menu_in_json_array() throws Exception {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        Dish soup = DishBuilder.aDish().withName("Tomatensoep").withPrice(2).withType(Type.Soep).build();
        Dish dagschotel = DishBuilder.aDish().withName("Frietjes").withPrice(4.6).withType(Type.Dagschotel).build();
        Dish veggie = DishBuilder.aDish().withName("Wortelen").withPrice(4).withType(Type.Veggie).build();

        DayMenu menu = new DayMenu(soup, dagschotel, veggie, today.format(formatter));

        Mockito.when(menuService.getWeekMenu()).thenReturn(Arrays.asList(menu));
        weekMenuController.perform(get("/weekmenu").contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath("$", hasSize(1)));
    }



}
