package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CustomPizza extends MenuItem {

    private int pizzaId;
    private BigDecimal price;
    private List<Ingredient> pizzaIngredients = new ArrayList<>();

    public CustomPizza() {

    }

    public CustomPizza(int pizzaId, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.price = price;
    }


    public CustomPizza(int pizzaId, String pizzaName, String pizzaDescription, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.price = price;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPizzaIngredients(List<Ingredient> pizzaIngredients) {
        this.pizzaIngredients = pizzaIngredients;
    }

    public List<Ingredient> getPizzaIngredients() {
        return pizzaIngredients;
    }
}
