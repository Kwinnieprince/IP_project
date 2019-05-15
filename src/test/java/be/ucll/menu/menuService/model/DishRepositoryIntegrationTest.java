package be.ucll.menu.menuService.model;

import be.ucll.menu.db.DishRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import static org.assertj.core.api.Assertions.assertThat;


import java.util.List;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DishRepositoryIntegrationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private DishRepository dishRepository;

    @Test
    public void returns_all_the_dishes_in_the_repository(){
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        testEntityManager.persist(dish1);
        testEntityManager.flush();

        Dish dish2 = new Dish("dagschotel", 4.6, Type.Dagschotel);
        testEntityManager.persist(dish2);
        testEntityManager.flush();

        List<Dish>dishesFound = dishRepository.findAll();

        assertThat(dishesFound.size()).isEqualTo(2);
        assertThat(dishesFound.contains(dish1)).isTrue();
        assertThat(dishesFound.contains(dish2)).isTrue();
    }

    @Test
    public void finds_dish_with_given_name(){
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        testEntityManager.persist(dish1);
        testEntityManager.flush();

        Dish foundDish = dishRepository.findByName(dish1.getName());

        assertThat(foundDish.getName()).isEqualTo(dish1.getName());
        assertThat(foundDish.getType()).isEqualTo(dish1.getType());
        assertThat(foundDish.getPrice()).isEqualTo(dish1.getPrice());
    }

    @Test
    public void adds_a_dish_to_the_repository(){
        Dish dish1 = new Dish("soep", 2.5, Type.Soep);
        testEntityManager.persist(dish1);
        testEntityManager.flush();

        Dish foundDish = dishRepository.save(dish1);

        assertThat(foundDish.getName()).isEqualTo(dish1.getName());
        assertThat(foundDish.getType()).isEqualTo(dish1.getType());
        assertThat(foundDish.getPrice()).isEqualTo(dish1.getPrice());
    }

}
