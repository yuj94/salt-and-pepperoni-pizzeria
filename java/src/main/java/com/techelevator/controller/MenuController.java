package com.techelevator.controller;

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
public class MenuController {
    private UserDao userDao;
    private MenuDao menuDao;
//
//
//
//    @RequestMapping(path = "/menu", method = RequestMethod.GET)
//    public List<MenuItem> getPizzas() {
//        return menuDao.getSpecialtyPizzaList();
//    }

//    @RequestMapping(path = "/menu/{itemId}", method = RequestMethod.GET)
//    public

}
