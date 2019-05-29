package be.ucll.menu.db;

import be.ucll.menu.menuService.model.Drank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Transactional
@Repository
public interface DrankRepository extends JpaRepository<Drank, Integer > {
    Drank findByName(String name);
}
