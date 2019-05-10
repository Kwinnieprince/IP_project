package be.ucll.menu.controller;

import be.ucll.menu.menuService.model.Dish;
import be.ucll.menu.menuService.model.MenuService;
import be.ucll.menu.menuService.model.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class MenuController implements WebMvcConfigurer {
    @Autowired
    private MenuService menuService;

    @GetMapping("/home")
    public String home(Model model){
        model.addAttribute("dishes", menuService.getDishes());
        return "index";
    }

    @GetMapping("/")
    public String start(Model model){
        model.addAttribute("dishes", menuService.getDishes());
        return "index";
    }

    @GetMapping(value = "/gerechten/change", params = {"name"})
    public String update(Model model, @RequestParam("name") String name){
        Dish dish = menuService.findDishByName(name);
        model.addAttribute("values", Type.values());
        model.addAttribute("dish", dish);
        return "update";
    }

    @PostMapping(value = "/gerechten/change")
    public String updateDish(@Valid Dish dish, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("values", Type.values());
            return "update";
        }else {
            menuService.updateDish(dish);
            model.addAttribute("dishes", menuService.getDishes());
            return "index";
        }
    }

    @GetMapping("/gerechten/add")
    public String showAddForm(Model model){
        model.addAttribute("values", Type.values());
        return "addDish";
    }

    @PostMapping("/gerechten/add")
    public String addMeal(@Valid Dish dish, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getFieldErrors());
            model.addAttribute("values", Type.values());
            return "addDish";
        } else {
            menuService.addDish(dish);
            model.addAttribute("dishes", menuService.getDishes());
            return "redirect:/";
        }
    }

    @GetMapping(value = "/gerechten/confirm", params = {"beschrijving"})
    public String gerechtConfirm(Model model, @RequestParam String beschrijving, BindingResult bindingResult) {
        Dish g = menuService.findDishByName(beschrijving);
        menuService.deleteDish(g);
        model.addAttribute("gerechten", menuService.getDishes());
        return "changeGerecht";
    }


    @GetMapping(value = "/gerechten/delete", params = {"name"})
     public String deleteDish(Model model, @RequestParam("name") String name){
        Dish dish = menuService.findDishByName(name);
        model.addAttribute("dish", dish);
        return "deleteDish";
    }

    @PostMapping(value = "/gerechten/delete")
    public String deleteDishData(@Valid Dish dish, BindingResult bindingResult, Model model){
        menuService.deleteDishByName(dish.getName());
        model.addAttribute("dishes", menuService.getDishes());
        return "redirect:/";
    }

}
