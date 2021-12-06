package com.techelevator.dao;

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

    public JdbcPizzaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Pizza> getPizzaList() {
        List<Pizza> pizzaList = new ArrayList<>();

        String sql = "SELECT * FROM pizza;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            pizzaList.add(mapRowToPizza(results));
        }

        return pizzaList;
    }

    @Override
    public Pizza getPizza(int pizzaId) {
        Pizza pizza = null;

        String sql = "SELECT * FROM pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, pizzaId);

        if(results.next()) {
            pizza = mapRowToPizza(results);
        }

        return pizza;
    }

    @Override
    public Pizza getCustomPizza(int newPizzaId) {
        Pizza pizza = null;

        String sql = "SELECT * FROM custom_pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newPizzaId);

        if(results.next()) {
            pizza = mapRowToCustomPizza(results);
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

        if (pizza.getIngredient1() != null) {
            price.add(getIngredientPrice(pizza.getIngredient1()));
        }
        if (pizza.getIngredient2() != null) {
            price.add(getIngredientPrice(pizza.getIngredient2()));
        }
        if (pizza.getIngredient3() != null) {
            price.add(getIngredientPrice(pizza.getIngredient3()));
        }
        if (pizza.getIngredient4() != null) {
            price.add(getIngredientPrice(pizza.getIngredient4()));
        }
        if (pizza.getIngredient5() != null) {
            price.add(getIngredientPrice(pizza.getIngredient5()));
        }
        if (pizza.getIngredient6() != null) {
            price.add(getIngredientPrice(pizza.getIngredient6()));
        }
        if (pizza.getIngredient7() != null) {
            price.add(getIngredientPrice(pizza.getIngredient7()));
        }
        if (pizza.getIngredient8() != null) {
            price.add(getIngredientPrice(pizza.getIngredient8()));
        }
        if (pizza.getIngredient9() != null) {
            price.add(getIngredientPrice(pizza.getIngredient9()));
        }
        if (pizza.getIngredient10() != null) {
            price.add(getIngredientPrice(pizza.getIngredient10()));
        }

        return price;
    }

    private BigDecimal getIngredientPrice(String ingredientName) {
        BigDecimal price;

        String sql = "SELECT price FROM ingredient WHERE ingredient_name = ?;";

        Integer results = jdbcTemplate.queryForObject(sql, Integer.class, ingredientName);

        price = BigDecimal.valueOf(results);

        return price;
    }

    private Pizza mapRowToPizza(SqlRowSet rowSet) {
        Pizza pizza = new Pizza();

        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setPizzaName(rowSet.getString("pizza_name"));
        pizza.setPizzaDescription(rowSet.getString("pizza_description"));
        pizza.setPrice(rowSet.getBigDecimal("price"));
        pizza.setIngredient1(rowSet.getString("ingredient_1"));
        pizza.setIngredient2(rowSet.getString("ingredient_2"));
        pizza.setIngredient3(rowSet.getString("ingredient_3"));
        pizza.setIngredient4(rowSet.getString("ingredient_4"));
        pizza.setIngredient5(rowSet.getString("ingredient_5"));
        pizza.setIngredient6(rowSet.getString("ingredient_6"));
        pizza.setIngredient7(rowSet.getString("ingredient_7"));
        pizza.setIngredient8(rowSet.getString("ingredient_8"));
        pizza.setIngredient9(rowSet.getString("ingredient_9"));
        pizza.setIngredient10(rowSet.getString("ingredient_10"));

        return pizza;
    }

    private Pizza mapRowToCustomPizza(SqlRowSet rowSet) {
        Pizza pizza = new Pizza();

        pizza.setPizzaId(rowSet.getInt("pizza_id"));
        pizza.setPrice(rowSet.getBigDecimal("price"));
        pizza.setIngredient1(rowSet.getString("ingredient_1"));
        pizza.setIngredient2(rowSet.getString("ingredient_2"));
        pizza.setIngredient3(rowSet.getString("ingredient_3"));
        pizza.setIngredient4(rowSet.getString("ingredient_4"));
        pizza.setIngredient5(rowSet.getString("ingredient_5"));
        pizza.setIngredient6(rowSet.getString("ingredient_6"));
        pizza.setIngredient7(rowSet.getString("ingredient_7"));
        pizza.setIngredient8(rowSet.getString("ingredient_8"));
        pizza.setIngredient9(rowSet.getString("ingredient_9"));
        pizza.setIngredient10(rowSet.getString("ingredient_10"));

        return pizza;
    }

}
