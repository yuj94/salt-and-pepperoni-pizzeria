package com.techelevator.dao;

import com.techelevator.model.CustomPizza;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Order;

import java.util.List;


public interface OrderDao {

        List<Order> getAllPendingOrders();

        List<Order> getCart();

        List<Order> getUncompletedOrdersByOrderId();

        List<MenuItem> getMenuItemsByOrderId(int orderId);

        List<CustomPizza> getCustomPizzasByOrderId(int orderId);
}
