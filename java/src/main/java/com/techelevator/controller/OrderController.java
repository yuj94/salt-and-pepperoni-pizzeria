package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.PizzaDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Pizza;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin
public class OrderController {

    private UserDao userDao;
    private PizzaDao pizzaDao;
    private OrderDao orderDao;

    public OrderController(UserDao userDao, PizzaDao pizzaDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.pizzaDao = pizzaDao;
        this.orderDao = orderDao;
    }

    @RequestMapping(path = "/menu", method = RequestMethod.GET)
    public List<Pizza> getPizzas() {
        return pizzaDao.getPizzaList();
    }
}
