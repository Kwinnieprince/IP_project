package be.ucll.menu.db;

import be.ucll.menu.menuService.model.DayMenu;
import be.ucll.menu.menuService.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
//@Transactional
public interface MenuRepository extends JpaRepository<DayMenu, LocalDate> {
    boolean findDayMenuBySoup(Dish soup);
    boolean findDayMenuByDagschotel(Dish dagschotel);
    boolean findDayMenuByVeggie(Dish veggie);
    DayMenu getDayMenuByDagschotel(Dish dagscotel);
    DayMenu getDayMenuByVeggie(Dish veggie);
    DayMenu getDayMenuBySoup(Dish soup);
}