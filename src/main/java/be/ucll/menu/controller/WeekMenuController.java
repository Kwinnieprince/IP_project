package be.ucll.menu.controller;

import be.ucll.menu.menuService.model.DayMenu;
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
    public List<DayMenu> addNewDayMenu(@RequestBody @Valid DayMenu dayMenu){
        System.out.println("first date: " + dayMenu.getDate().toString());
        menuService.addDayMenu(dayMenu);
        return menuService.getWeekMenu();
    }

    @PostMapping(value = "/dagmenu/change/{date}")
    public List<DayMenu> changeDayMenu(  @PathVariable("date") String date, @RequestBody @Valid DayMenu dayMenu){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateFromUrl = LocalDate.parse(date, formatter);
        return menuService.changeDayMenu(dateFromUrl, dayMenu);
        //@PathVariable("date") @DateTimeFormat(pattern = "dd-MM-yyyy")
    }

}