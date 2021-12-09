package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import com.techelevator.model.CustomPizza;

import java.util.List;

public interface MenuDao {

    List<MenuItem> getAllMenuItems();

    List<MenuItem> getSpecialtyPizzaList();

    List<MenuItem> getAppetizerList();

    List<MenuItem> getSaladList();

    List<MenuItem> getDessertList();

    List<MenuItem> getDrinkList();

    MenuItem getCustomPizza(int newPizzaId);

    List<Ingredient> getMenuPizzaIngredients(int pizzaId);

    List<Ingredient> getIngredientList();

    boolean createCustomPizza(CustomPizza customPizza);

}
