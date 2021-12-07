package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.Pizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPizzaDao implements PizzaDao {

    private JdbcTemplate jdbcTemplate;
    private static final String CUSTOM_PIZZA = "Custom";
    private static final String SPECIALTY_PIZZA = "Specialty";
    private static final int MAX_INGREDIENTS_ALLOWED = 10;

    public JdbcPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pizza> getPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();

        String sql = "SELECT special_pizza.pizza_id, pizza_name, pizza_description, pizza.price FROM special_pizza\n" +
                "FULL OUTER JOIN pizza ON pizza.pizza_id = special_pizza.pizza_id;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            pizzaList.add(mapRowToPizza(SPECIALTY_PIZZA, results));
        }

        for (Pizza pizza: pizzaList) {
//            pizza.setPrice(getPizzaPrice(pizza));
            pizza.setPizzaIngredients(getPizzaIngredients(pizza.getPizzaId()));
        }

        return pizzaList;
    }

    @Override
    public List<Ingredient> getPizzaIngredients(int pizzaId) {
        List<Ingredient> ingredients = new ArrayList<>();

        String sql = "SELECT ingredient.ingredient_id, ingredient_name, ingredient_type, ingredient.price, ingredient.quantity AS total_quantity, pizza_ingredient.quantity AS order_quantity FROM ingredient\n" +
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
    public Pizza getCustomPizza(int customPizzaId) {
        Pizza pizza = null;

        String sql = "SELECT pizza_id, price FROM pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customPizzaId);

        if(results.next()) {
            pizza = mapRowToPizza(CUSTOM_PIZZA, results);
        }

        pizza.setPizzaIngredients(getPizzaIngredients(pizza.getPizzaId()));

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

    private void setPizzaPrice(Pizza pizza) {
        String sql = "UPDATE pizza SET price = ? WHERE pizza_id = ?;";

        jdbcTemplate.update(sql, calculatePizzaPrice(pizza), pizza.getPizzaId());
    }

    private BigDecimal getPizzaPrice(Pizza pizza) {
        setPizzaPrice(pizza);
        String sql = "SELECT price FROM pizza WHERE pizza_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getPizzaId());
    }

    private BigDecimal calculatePizzaPrice(Pizza pizza) {
        String sql = "SELECT SUM(price) FROM ingredient\n" +
                "INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                "WHERE pizza_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getPizzaId());
    }

    private Pizza mapRowToPizza(String pizzaType, SqlRowSet rowSet) {
        Pizza pizza = new Pizza();

        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setPrice(getPizzaPrice(pizza));

        if (pizzaType == SPECIALTY_PIZZA) {
            pizza.setPizzaName(rowSet.getString("pizza_name"));
            pizza.setPizzaDescription(rowSet.getString("pizza_description"));
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

}
