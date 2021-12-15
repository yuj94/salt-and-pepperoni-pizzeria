package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import com.techelevator.model.CustomPizza;

import java.math.BigDecimal;
import java.util.List;

public interface MenuDao {

    List<MenuItem> getAllMenuItems();

    MenuItem getCustomPizza(int newPizzaId);

    List<Ingredient> getMenuPizzaIngredients(int pizzaId);

    List<Ingredient> getIngredientList();

    int createCustomPizza(CustomPizza customPizza);

    int updateIngredientQuantity(int ingredientId, double quantity);

    int updateIngredientPrice(int ingredientId, BigDecimal price);

    int updateMenuItemQuantity(int itemId, double quantity);

    int updateMenuItemPrice(int itemId, BigDecimal price);

    int addIngredient (Ingredient ingredient);

    int createMenuItem(MenuItem menuItem);
}
