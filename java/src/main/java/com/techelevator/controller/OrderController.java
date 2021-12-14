package com.techelevator.controller;

import com.techelevator.dao.OrderDao;
import com.techelevator.dao.MenuDao;
import com.techelevator.dao.UserDao;
import com.techelevator.model.CustomPizza;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Order;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    public List<Order> getCompletedOrders(String parameterType, String searchText) {
        return orderDao.getAllHistoricalOrders();
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/pending/menuItem/{orderId}", method = RequestMethod.GET)
    public List<MenuItem> getMenuItemsByOrderId(Principal principal, @PathVariable int orderId){
        return orderDao.getMenuItemsByOrderId(orderId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/pending/customPizza/{orderId}", method = RequestMethod.GET)
    public List<CustomPizza> getCustomPizzasByOrderId(Principal principal, @PathVariable int orderId){
        return orderDao.getCustomPizzasByOrderId(orderId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/pending/markComplete/{orderId}", method = RequestMethod.PUT)
    public void setOrderToComplete(Principal principal, @PathVariable int orderId){
        orderDao.setOrderToComplete(orderId);
    }

    @PreAuthorize("isAuthenticated()")
    @RequestMapping(path = "/order/pending/markNotComplete/{orderId}", method = RequestMethod.PUT)
    public void setOrderToNotComplete(Principal principal, @PathVariable int orderId){
        orderDao.setOrderToNotComplete(orderId);
    }
}
