package com.techelevator.model;

import java.math.BigDecimal;

public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String ingredientType;
    private BigDecimal price;
    private Double orderQuantity;
    private Double totalQuantity;

    public Ingredient() {

    }
    public Ingredient(int ingredientId, String ingredientName, String ingredientType, BigDecimal price, Double orderQuantity, Double totalQuantity) {
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

    public Double getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Double orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public Double getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(Double totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
}
