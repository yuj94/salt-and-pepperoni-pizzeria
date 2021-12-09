package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.MenuDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Order;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/pending", method = RequestMethod.GET)
    public List<Order> getAllPendingOrders() {
        return orderDao.getAllPendingOrders();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/history", method = RequestMethod.GET)
    public List<Order> getOrdersBySearch(String parameterType, String searchText) {
        return orderDao.getOrdersBySearch(parameterType, searchText);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/cart", method = RequestMethod.GET)
    public List<Order> getCart() {
        return orderDao.getCart();
    }


}
