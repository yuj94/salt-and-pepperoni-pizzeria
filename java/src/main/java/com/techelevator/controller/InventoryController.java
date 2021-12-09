package com.techelevator.controller;

import com.techelevator.dao.MenuDao;
import com.techelevator.dao.OrderDao;
import com.techelevator.dao.UserDao;

public class InventoryController {

    private UserDao userDao;
    private MenuDao menuDao;
    private OrderDao orderDao;

    public InventoryController(UserDao userDao, MenuDao menuDao, OrderDao orderDao) {
        this.userDao = userDao;
        this.menuDao = menuDao;
        this.orderDao = orderDao;
    }

}
