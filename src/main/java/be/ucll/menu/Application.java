package be.ucll.menu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

//    @Bean
//    @Order
//    CommandLineRunner runnerDish(DishRepository repository){
//        return DishArgs -> {
//            repository.save(new Dish("Balletjes", 2.5, Type.Dagschotel));
//            repository.save(new Dish("Soep", 1.5, Type.Soep));
//            repository.save(new Dish("Kip", 4, Type.Dagschotel));
//            repository.save(new Dish("Rijst", 2.5, Type.Veggie));
//        };
//    }
}

