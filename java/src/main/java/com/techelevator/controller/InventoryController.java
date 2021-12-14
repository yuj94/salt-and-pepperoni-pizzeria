package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.Order;
import com.techelevator.security.jwt.TokenProvider;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class InventoryController {

    private UserDao userDao;
    private MenuDao menuDao;
    private OrderDao orderDao;

    public InventoryController(UserDao userDao, MenuDao menuDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.menuDao = menuDao;
        this.orderDao = orderDao;
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/Inventory", method = RequestMethod.GET)
    public void getInventory() {

    }

}
