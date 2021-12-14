package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.CustomPizza;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
public class MenuController {
    private MenuDao menuDao;

    public MenuController(MenuDao menuDao) {
        this.menuDao = menuDao;
    }

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public List<MenuItem> getAllMenuItems() {
        return menuDao.getAllMenuItems();
    }

    @RequestMapping(path = "/ingredients", method = RequestMethod.GET)
    public List<Ingredient> getIngredientList() {
        return menuDao.getIngredientList();
    }

    @RequestMapping(path = "/custom-pizza", method = RequestMethod.POST)
    public int createCustomPizza(@RequestBody CustomPizza customPizza) { return menuDao.createCustomPizza(customPizza);}
}
