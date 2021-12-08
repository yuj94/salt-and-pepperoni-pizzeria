package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Pizza;

import java.util.List;

public interface MenuDao {

    public List<MenuItem> getAllMenuItems();

    List<MenuItem> getSpecialtyPizzaList();

    MenuItem getCustomPizza(int newPizzaId);

    List<Ingredient> getPizzaIngredients(int pizzaId);

    List<Ingredient> getIngredientList();

    boolean createCustomPizza(Pizza pizza);

}
