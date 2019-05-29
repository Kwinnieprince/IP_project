package be.ucll.menu.controller;

import be.ucll.menu.menuService.model.DayMenu;
import be.ucll.menu.menuService.model.Drank;
import be.ucll.menu.menuService.model.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
public class WeekMenuController {
    @Autowired
    private MenuService menuService;

    @GetMapping("/weekmenu")
    public List<DayMenu> getWeekMenu() {
        return menuService.getWeekMenu();
    }

    @PostMapping("/dagmenu/add")
    public List<DayMenu> addNewDayMenu(@RequestBody @Valid DayMenu dayMenu) {
        menuService.addDayMenu(dayMenu);
        return menuService.getWeekMenu();
    }

    @PostMapping(value = "/dagmenu/change/{date}")
    public List<DayMenu> changeDayMenu(  @PathVariable("date") String date, @RequestBody @Valid DayMenu dayMenu){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);
        return menuService.changeDayMenu(dateFromUrl, dayMenu);
    }

    @PostMapping(value = "/dagmenu/delete/{date}")
    public void deleteDayMenu(@PathVariable String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);
        menuService.deleteDayMenu(dateFromUrl);
    }

    @PostMapping(value = "/weekmenu/delete/{date}")
    public void deleteWeekMenu(@PathVariable String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);
        menuService.deleteWeekMenu(dateFromUrl);
    }

    @GetMapping(value = "/dagmenu")
    public DayMenu returnDayMenuOfToday(){
        DayMenu menu =  menuService.getDayMenuToday();
        menu.addDrinks(menuService.getRandomDrinkAlcohol(), menuService.getRandomDrinkNonAlcohol());
        return menu;
    }

    @GetMapping(value = "/dranken")
    public List<Drank>returnEveryDrink(){
        return menuService.getAllDrinks();
    }

    @PostMapping(value = "/drank/add")
    public Drank addDrinkToDatabase(@RequestBody @Valid Drank drank){
        return menuService.addDrank(drank);
    }

    @PostMapping(value = "/drank/update/{name}")
    public Drank updateDrinkToDatabase(@PathVariable String name,@RequestBody @Valid Drank drank){
        return menuService.updateDrank(name, drank);
    }

    @PostMapping(value = "/drank/delete/{name}")
    public void deleteDrank(@PathVariable String name){
        menuService.deleteDrink(name);
    }

}