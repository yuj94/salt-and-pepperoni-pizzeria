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
    private static final String CUSTOM_PIZZA = "Custom Pizza";
    private static final String SPECIALTY_PIZZA = "Specialty Pizza";

    public JdbcPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pizza> getPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();

        String sql = "SELECT * FROM pizza;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            pizzaList.add(mapRowToPizza(SPECIALTY_PIZZA, results));
        }

        return pizzaList;
    }

    @Override
    public Pizza getPizza(int pizzaId) {
        Pizza pizza = null;

        String sql = "SELECT * FROM pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaId);

        if(results.next()) {
            pizza = mapRowToPizza(SPECIALTY_PIZZA, results);
        }

        return pizza;
    }

    @Override
    public Ingredient getIngredient(String ingredientName) {
        Ingredient ingredient = null;

        String sql = "SELECT * FROM ingredient WHERE ingredient_name = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, ingredientName);

        if(results.next()) {
            ingredient = mapRowToIngredient(results);
        }

        return ingredient;
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
    public Pizza getCustomPizza(int newPizzaId) {
        Pizza pizza = null;

        String sql = "SELECT * FROM custom_pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newPizzaId);

        if(results.next()) {
            pizza = mapRowToPizza(CUSTOM_PIZZA, results);
        }

        return pizza;
    }

    @Override
    public boolean createCustomPizza(Pizza pizza) {
        boolean isCreated = false;

        String sql = "INSERT INTO custom_pizza (price, ingredient_1, ingredient_2, ingredient_3, ingredient_4, ingredient_5, ingredient_6, ingredient_7, ingredient_8, ingredient_9, ingredient_10) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING pizza_id;";

        pizza.setPrice(getCustomPizzaPrice(pizza));

        Integer newPizzaId = jdbcTemplate.queryForObject(sql, Integer.class, pizza.getPrice(), pizza.getIngredient1(), pizza.getIngredient2(), pizza.getIngredient3(), pizza.getIngredient4(), pizza.getIngredient5(), pizza.getIngredient6(), pizza.getIngredient7(), pizza.getIngredient8(), pizza.getIngredient9(), pizza.getIngredient10());

        if(getCustomPizza(newPizzaId) != null) {
            isCreated = true;
        }

        return isCreated;
    }

    private BigDecimal getCustomPizzaPrice(Pizza pizza) {
        BigDecimal price = BigDecimal.valueOf(0);

        for (Ingredient ingredient: pizza.getPizzaIngredients()) {
            if (ingredient != null) {
                price.add(getIngredientPrice(ingredient));
            }
        }

        return price;
    }

    private BigDecimal getIngredientPrice(Ingredient ingredient) {
        BigDecimal price;

        String sql = "SELECT price FROM ingredient WHERE ingredient_name = ?;";

        Integer results = jdbcTemplate.queryForObject(sql, Integer.class, ingredient.getIngredientName());

        price = BigDecimal.valueOf(results);

        return price;
    }

    private Pizza mapRowToPizza(String pizzaType, SqlRowSet rowSet) {
        Pizza pizza = new Pizza();

        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setPrice(rowSet.getBigDecimal("price"));
        pizza.setIngredient1(getIngredient(rowSet.getString("ingredient_1")));
        pizza.setIngredient2(getIngredient(rowSet.getString("ingredient_2")));
        pizza.setIngredient3(getIngredient(rowSet.getString("ingredient_3")));
        pizza.setIngredient4(getIngredient(rowSet.getString("ingredient_4")));
        pizza.setIngredient5(getIngredient(rowSet.getString("ingredient_5")));
        pizza.setIngredient6(getIngredient(rowSet.getString("ingredient_6")));
        pizza.setIngredient7(getIngredient(rowSet.getString("ingredient_7")));
        pizza.setIngredient8(getIngredient(rowSet.getString("ingredient_8")));
        pizza.setIngredient9(getIngredient(rowSet.getString("ingredient_9")));
        pizza.setIngredient10(getIngredient(rowSet.getString("ingredient_10")));

        if (pizzaType == SPECIALTY_PIZZA) {
            pizza.setPizzaName(rowSet.getString("pizza_name"));
            pizza.setPizzaDescription(rowSet.getString("pizza_description"));
        }

        return pizza;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientName(rowSet.getString("ingredient_name"));
        ingredient.setIngredientType(rowSet.getString("ingredient_type"));
        ingredient.setPrice(rowSet.getBigDecimal("price"));
        ingredient.setQuantity(rowSet.getDouble("quantity"));

        return ingredient;
    }

}
