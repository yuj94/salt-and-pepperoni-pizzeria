package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Pizza;

import java.util.List;

public interface PizzaDao {

    List<Pizza> getPizzaList();

    Pizza getPizza(int newPizzaId);

    Pizza getCustomPizza(int newPizzaId);

    Ingredient getIngredient(String ingredientName);

    List<Ingredient> getIngredientList();

    boolean createCustomPizza(Pizza pizza);

}
