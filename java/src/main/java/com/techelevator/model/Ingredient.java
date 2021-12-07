package com.techelevator.model;

import java.math.BigDecimal;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String ingredientType;
    private BigDecimal price;
    private double orderQuantity;
    private double totalQuantity;

    public Ingredient() {

    }
    public Ingredient(int ingredientId, String ingredientName, String ingredientType, BigDecimal price, double orderQuantity, double totalQuantity) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.ingredientType = ingredientType;
        this.price = price;
        this.orderQuantity = orderQuantity;
        this.totalQuantity = totalQuantity;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
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

    public double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
