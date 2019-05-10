package be.ucll.menu.menuService.model;

import be.ucll.menu.db.MenuRepository;
import com.sun.xml.internal.xsom.impl.parser.SubstGroupBaseTypeRef;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class MenuRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private MenuRepository menuRepository;

    @Test
    public void returns_all_the_menu_s_in_the_menu_repository(){
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        Dish dish2 = new Dish("dagschotel", 4.6, Type.Dagschotel);
        Dish dish3 = new Dish("rijst", 2.6, Type.Veggie);
        DayMenu menu = new DayMenu(dish1, dish2, dish3, "10-05-2019");
        testEntityManager.persist(menu);
        testEntityManager.flush();

        List<DayMenu>menus = menuRepository.findAll();

        assertThat(menus.size()).isEqualTo(1);
        assertThat(menus.contains(menu)).isTrue();

    }

    @Test
    public void returns_day_menu_of_given_day(){
        String date = "10-05-2019";
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        Dish dish2 = new Dish("dagschotel", 4.6, Type.Dagschotel);
        Dish dish3 = new Dish("rijst", 2.6, Type.Veggie);
        DayMenu menu = new DayMenu(dish1, dish2, dish3, date );
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateLocal = LocalDate.parse(date, formatter);
        testEntityManager.persist(menu);
        testEntityManager.flush();

        DayMenu foundMenu = menuRepository.findById(dateLocal).orElseThrow(IllegalArgumentException::new);

        assertThat(foundMenu.getDagschotel()).isEqualTo(menu.getDagschotel());
        assertThat(foundMenu.getSoup()).isEqualTo(menu.getSoup());
        assertThat(foundMenu.getVeggie()).isEqualTo(menu.getVeggie());
    }

    @Test
    public void adds_menu_to_the_repository(){
        String date = "10-05-2019";
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        Dish dish2 = new Dish("dagschotel", 4.6, Type.Dagschotel);
        Dish dish3 = new Dish("rijst", 2.6, Type.Veggie);
        DayMenu menu = new DayMenu(dish1, dish2, dish3, date );
        testEntityManager.persist(menu);
        testEntityManager.flush();

        DayMenu savedMenu = menuRepository.save(menu);

        assertThat(savedMenu.getDagschotel()).isEqualTo(menu.getDagschotel());
        assertThat(savedMenu.getSoup()).isEqualTo(menu.getSoup());
        assertThat(savedMenu.getVeggie()).isEqualTo(menu.getVeggie());
    }

}
