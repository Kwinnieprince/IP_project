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
        Dish dish = DishBuilder.aOkDish().build();

        testEntityManager.persist(dish);
        testEntityManager.flush();

        List<Dish> dishes = dishRepository.findAll();

        assertThat(dishes.size()).isEqualTo(1);
        assertThat(dishes).contains(dish);
    }

    @Test
    public void finds_dish_with_given_name(){
        Dish dish = DishBuilder.aDish().withName("Balletjes").withPrice(4.5).withType(Type.Dagschotel).build();

        testEntityManager.persist(dish);
        testEntityManager.flush();

        Dish foundDish = dishRepository.findByName("Balletjes");

        assertThat(foundDish).isEqualTo(dish);
    }

    @Test
    public void adds_a_dish_to_the_repository(){
        Dish dish = DishBuilder.aOkDish().build();

        Dish savedDish = dishRepository.save(dish);

        assertThat(dish).isEqualTo(savedDish);
    }

}
