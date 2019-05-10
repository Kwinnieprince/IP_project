package be.ucll.menu.db;

import be.ucll.menu.menuService.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@Repository
public interface DishRepository extends JpaRepository<Dish, Integer > {
    @Override
    List<Dish> findAll();

    Dish findByName(String name);

    void deleteDishByName(String name);

    boolean existsDishByName(String name);

}
