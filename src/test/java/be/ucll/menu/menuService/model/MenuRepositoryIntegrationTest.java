package be.ucll.menu.menuService.model;

import be.ucll.menu.db.MenuRepository;
import com.sun.xml.internal.xsom.impl.parser.SubstGroupBaseTypeRef;
import org.apache.tomcat.jni.Local;
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
        DayMenu menu = DayMenuBuilder.anOkDayMenu(LocalDate.now()).build();

        testEntityManager.persist(menu);
        testEntityManager.flush();

        List<DayMenu>menus = menuRepository.findAll();

        assertThat(menus.size()).isEqualTo(1);
        assertThat(menus).contains(menu);

    }

    @Test
    public void returns_day_menu_of_given_day(){
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        Dish dish2 = new Dish("dagschotel", 4.6, Type.Dagschotel);
        Dish dish3 = new Dish("rijst", 2.6, Type.Veggie);
        LocalDate date = LocalDate.now();
        DayMenu menu = DayMenuBuilder.anDayMenu().withSoup(dish1).withDagschotel(dish2).withVeggie(dish3).onDate(date).build();

        testEntityManager.persist(menu);
        testEntityManager.flush();

        DayMenu foundMenu = menuRepository.findById(date).orElseThrow(IllegalArgumentException::new);

        assertThat(foundMenu.getDagschotel()).isEqualTo(menu.getDagschotel());
        assertThat(foundMenu.getSoup()).isEqualTo(menu.getSoup());
        assertThat(foundMenu.getVeggie()).isEqualTo(menu.getVeggie());
    }

    @Test
    public void adds_menu_to_the_repository(){
        DayMenu menu = DayMenuBuilder.anOkDayMenu(LocalDate.now()).build();

        testEntityManager.persist(menu);
        testEntityManager.flush();

        DayMenu savedMenu = menuRepository.save(menu);

        assertThat(savedMenu).isEqualTo(menu);
    }

}
