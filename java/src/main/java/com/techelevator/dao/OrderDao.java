package com.techelevator.dao;

import com.techelevator.model.Order;

import java.util.List;


public interface OrderDao {

        List<Order> getAllPendingOrders();

        List<Order> getCart();
}
