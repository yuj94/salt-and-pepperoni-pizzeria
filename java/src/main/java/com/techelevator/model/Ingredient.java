package com.techelevator.model;

import java.math.BigDecimal;

public class Ingredient {
    private String ingredientName;
    private String ingredientType;
    private BigDecimal price;
    private double quantity;

    public Ingredient() {
    }

    public Ingredient(String ingredientName, String ingredientType, BigDecimal price, double quantity) {
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.price = price;
        this.quantity = quantity;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getIngredientType() {
        return ingredientType;
    }

    public void setIngredientType(String ingredientType) {
        this.ingredientType = ingredientType;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }
}
