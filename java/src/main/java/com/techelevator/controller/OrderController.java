package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.MenuDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MenuItem;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    private UserDao userDao;
    private MenuDao menuDao;
    private OrderDao orderDao;

    public OrderController(UserDao userDao, MenuDao menuDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.menuDao = menuDao;
        this.orderDao = orderDao;
    }

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public List<MenuItem> getAllMenuItems() {
        return menuDao.getAllMenuItems();
    }

    @RequestMapping(path = "/menu/pizza", method = RequestMethod.GET)
    public List<MenuItem> getPizzas() {
        return menuDao.getSpecialtyPizzaList();
    }

}
