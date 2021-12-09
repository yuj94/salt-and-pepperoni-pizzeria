package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MenuItem;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(path = "/menu/pizzas", method = RequestMethod.GET)
    public List<MenuItem> getSpecialtyPizzaList() {
        return menuDao.getSpecialtyPizzaList();
    }

    @RequestMapping(path = "/menu/appetizers", method = RequestMethod.GET)
    public List<MenuItem> getAppetizerList() {
        return menuDao.getAppetizerList();
    }

    @RequestMapping(path = "/menu/salads", method = RequestMethod.GET)
    public List<MenuItem> getSaladList() {
        return menuDao.getSaladList();
    }

    @RequestMapping(path = "/menu/desserts", method = RequestMethod.GET)
    public List<MenuItem> getDessertList() {
        return menuDao.getDessertList();
    }

    @RequestMapping(path = "/menu/drinks", method = RequestMethod.GET)
    public List<MenuItem> getDrinkList() {
        return menuDao.getDrinkList();
    }

}
