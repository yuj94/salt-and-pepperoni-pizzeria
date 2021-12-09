package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import com.techelevator.model.Order;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements OrderDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Order> getAllPendingOrders(){
        List<Order> pendingOrderList = new ArrayList<>();

       // String sql =
        return pendingOrderList;
    }


    public List<Order> getCart(){
        List<Order> pendingOrderList = new ArrayList<>();

        // String sql =
        return pendingOrderList;
    }

    }


