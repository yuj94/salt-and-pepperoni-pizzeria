package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Pizza;

import java.util.List;

public interface PizzaDao {

    List<Pizza> getPizzaList();

    Pizza getCustomPizza(int newPizzaId);

    List<Ingredient> getPizzaIngredients(int pizzaId);

    List<Ingredient> getIngredientList();

    boolean createCustomPizza(Pizza pizza);

}
