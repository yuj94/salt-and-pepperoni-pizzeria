package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import com.techelevator.model.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMenuDao implements MenuDao {

    private JdbcTemplate jdbcTemplate;
    private static final String PIZZA_CATEGORY = "Pizza";
    private static final String APPETIZER_CATEGORY = "Appetizer";
    private static final String DESSERT_CATEGORY = "Dessert";
    private static final String SALAD_CATEGORY = "Salad";
    private static final String DRINK_CATEGORY = "Drink";
    private static final String CUSTOM_PIZZA = "Custom";
    private static final String SPECIALTY_PIZZA = "Specialty";
    private static final int MAX_INGREDIENTS_ALLOWED = 10;

    public JdbcMenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();

        String sql = "SELECT item_id, item_name, item_description, item_category, price, total_quantity FROM menu;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            menuItemList.add(mapRowToMenuItem(results));
        }

        return menuItemList;
    }

    @Override
    public List<MenuItem> getSpecialtyPizzaList() {
        List<MenuItem> pizzaList = new ArrayList<>();

        String sql = "SELECT item_id, item_name, item_description, item_category, price, total_quantity FROM menu\n" +
                "WHERE item_category = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, PIZZA_CATEGORY);

        while (results.next()) {
            pizzaList.add(mapRowToPizza(SPECIALTY_PIZZA, results));
        }

        for (MenuItem pizza: pizzaList) {
            pizza.setIngredientList(getPizzaIngredients(pizza.getItemId()));
        }

        return pizzaList;
    }

    @Override
    public List<Ingredient> getPizzaIngredients(int pizzaId) {
        List<Ingredient> ingredients = new ArrayList<>();

        String sql = "SELECT ingredient.ingredient_id, ingredient_name, ingredient_type, ingredient.price, ingredient.total_quantity AS total_quantity, pizza_ingredient.quantity AS order_quantity FROM ingredient\n" +
                "INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                "WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaId);

        while(results.next()) {
            ingredients.add(mapRowToIngredient(results));
        }

        return ingredients;
    }

    @Override
    public List<Ingredient> getIngredientList() {
        List<Ingredient> ingredientList = new ArrayList<>();

        String sql = "SELECT * FROM ingredient;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            ingredientList.add(mapRowToIngredient(results));
        }

        return ingredientList;
    }

    @Override
    public MenuItem getCustomPizza(int customPizzaId) {
        MenuItem pizza = null;

        String sql = "SELECT pizza_id, price FROM pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customPizzaId);

        if(results.next()) {
            pizza = mapRowToPizza(CUSTOM_PIZZA, results);
        }

        pizza.setIngredientList(getPizzaIngredients(pizza.getItemId()));

        return pizza;
    }

    @Override
    public boolean createCustomPizza(Pizza pizza) {
        boolean isCreated = false;

        if (pizza.getPizzaIngredients().size() <= MAX_INGREDIENTS_ALLOWED) {
            String sql = "INSERT INTO pizza (pizza_type) " +
                    "VALUES (?) RETURNING pizza_id;";

            pizza.setPizzaId(jdbcTemplate.queryForObject(sql, Integer.class, CUSTOM_PIZZA));

            addIngredientsToPizzaIngredientTable(pizza);

            if (getCustomPizza(pizza.getPizzaId()) != null) {
                isCreated = true;
            }
        }

        return isCreated;
    }

    private void addIngredientsToPizzaIngredientTable(Pizza pizza) {
        String sql = "INSERT INTO pizza_ingredient (pizza_id, ingredient_id, quantity) " +
                "VALUES (?, ?, ?);";

        for (int i = 0; i < pizza.getPizzaIngredients().size(); i++) {
            jdbcTemplate.update(sql, pizza.getPizzaId(), pizza.getPizzaIngredients().get(i).getIngredientId(), pizza.getPizzaIngredients().get(i).getOrderQuantity());
        }

    }

    private void setPizzaPrice(MenuItem pizza) {
        String sql = "UPDATE menu SET price = ? WHERE item_id = ?;";

        jdbcTemplate.update(sql, calculatePizzaPrice(pizza), pizza.getItemId());
    }

    private BigDecimal getPizzaPrice(MenuItem pizza) {
        setPizzaPrice(pizza);
        String sql = "SELECT price FROM menu WHERE item_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getItemId());
    }

    private BigDecimal calculatePizzaPrice(MenuItem pizza) {
        String sql = "SELECT SUM(price) FROM ingredient\n" +
                "INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                "WHERE pizza_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getItemId());
    }

    private MenuItem mapRowToPizza(String pizzaType, SqlRowSet rowSet) {
        MenuItem pizza = new MenuItem();

        pizza.setItemId(rowSet.getInt("item_id"));
        pizza.setPrice(getPizzaPrice(pizza));
        pizza.setItemCategory(rowSet.getString("item_category"));

        if (pizzaType == SPECIALTY_PIZZA) {
            pizza.setItemName(rowSet.getString("item_name"));
            pizza.setItemDescription(rowSet.getString("item_description"));
        }

        return pizza;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientId(rowSet.getInt("ingredient_id"));
        ingredient.setIngredientName(rowSet.getString("ingredient_name"));
        ingredient.setIngredientType(rowSet.getString("ingredient_type"));
        ingredient.setPrice(rowSet.getBigDecimal("price"));
        ingredient.setTotalQuantity(rowSet.getDouble("total_quantity"));
        ingredient.setOrderQuantity(rowSet.getDouble("order_quantity"));

        return ingredient;
    }

    private MenuItem mapRowToMenuItem(SqlRowSet rowSet) {
        MenuItem menuItem = new MenuItem();

        menuItem.setItemId(rowSet.getInt("item_id"));
        menuItem.setPrice(rowSet.getBigDecimal("price"));
        menuItem.setItemCategory(rowSet.getString("item_category"));
        menuItem.setItemName(rowSet.getString("item_name"));
        menuItem.setItemDescription(rowSet.getString("item_description"));
        menuItem.setTotalQuantity(rowSet.getDouble("total_quantity"));

        return menuItem;
    }

}
