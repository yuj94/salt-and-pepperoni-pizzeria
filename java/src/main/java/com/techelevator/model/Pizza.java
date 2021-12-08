package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Pizza extends MenuItem {

    private int pizzaId;
    private String pizzaName;
    private String pizzaDescription;
    private BigDecimal price;
    private List<Ingredient> pizzaIngredients = new ArrayList<>();

    public Pizza() {

    }

    public Pizza(int pizzaId, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.price = price;
    }


    public Pizza(int pizzaId, String pizzaName, String pizzaDescription, BigDecimal price) {
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.pizzaDescription = pizzaDescription;
        this.price = price;
    }

    public int getPizzaId() {
        return pizzaId;
    }

    public void setPizzaId(int pizzaId) {
        this.pizzaId = pizzaId;
    }

    public String getPizzaName() {
        return pizzaName;
    }

    public void setPizzaName(String pizzaName) {
        this.pizzaName = pizzaName;
    }

    public String getPizzaDescription() {
        return pizzaDescription;
    }

    public void setPizzaDescription(String pizzaDescription) {
        this.pizzaDescription = pizzaDescription;
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
