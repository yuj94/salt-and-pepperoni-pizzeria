package com.techelevator.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Pizza {

    private int pizzaId;
    private String pizzaName;
    private String pizzaDescription;
    private BigDecimal price;
    private Ingredient ingredient1;
    private Ingredient ingredient2;
    private Ingredient ingredient3;
    private Ingredient ingredient4;
    private Ingredient ingredient5;
    private Ingredient ingredient6;
    private Ingredient ingredient7;
    private Ingredient ingredient8;
    private Ingredient ingredient9;
    private Ingredient ingredient10;

    public List<Ingredient> getPizzaIngredients() {
        List<Ingredient> listOfPizzaIngredients = Arrays.asList(ingredient1, ingredient2, ingredient3, ingredient4, ingredient5, ingredient6,
                ingredient7, ingredient8, ingredient9, ingredient10);

        return listOfPizzaIngredients;
    }

    public Pizza() {

    }

    public Pizza(int pizzaId, String pizzaName, String pizzaDescription, BigDecimal price, Ingredient ingredient1, Ingredient ingredient2, Ingredient ingredient3, Ingredient ingredient4, Ingredient ingredient5, Ingredient ingredient6, Ingredient ingredient7, Ingredient ingredient8, Ingredient ingredient9, Ingredient ingredient10) {
        this.pizzaId = pizzaId;
        this.pizzaName = pizzaName;
        this.pizzaDescription = pizzaDescription;
        this.price = price;
        this.ingredient1 = ingredient1;
        this.ingredient2 = ingredient2;
        this.ingredient3 = ingredient3;
        this.ingredient4 = ingredient4;
        this.ingredient5 = ingredient5;
        this.ingredient6 = ingredient6;
        this.ingredient7 = ingredient7;
        this.ingredient8 = ingredient8;
        this.ingredient9 = ingredient9;
        this.ingredient10 = ingredient10;
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

    public Ingredient getIngredient1() {
        return ingredient1;
    }

    public void setIngredient1(Ingredient ingredient1) {
        this.ingredient1 = ingredient1;
    }

    public Ingredient getIngredient2() {
        return ingredient2;
    }

    public void setIngredient2(Ingredient ingredient2) {
        this.ingredient2 = ingredient2;
    }

    public Ingredient getIngredient3() {
        return ingredient3;
    }

    public void setIngredient3(Ingredient ingredient3) {
        this.ingredient3 = ingredient3;
    }

    public Ingredient getIngredient4() {
        return ingredient4;
    }

    public void setIngredient4(Ingredient ingredient4) {
        this.ingredient4 = ingredient4;
    }

    public Ingredient getIngredient5() {
        return ingredient5;
    }

    public void setIngredient5(Ingredient ingredient5) {
        this.ingredient5 = ingredient5;
    }

    public Ingredient getIngredient6() {
        return ingredient6;
    }

    public void setIngredient6(Ingredient ingredient6) {
        this.ingredient6 = ingredient6;
    }

    public Ingredient getIngredient7() {
        return ingredient7;
    }

    public void setIngredient7(Ingredient ingredient7) {
        this.ingredient7 = ingredient7;
    }

    public Ingredient getIngredient8() {
        return ingredient8;
    }

    public void setIngredient8(Ingredient ingredient8) {
        this.ingredient8 = ingredient8;
    }

    public Ingredient getIngredient9() {
        return ingredient9;
    }

    public void setIngredient9(Ingredient ingredient9) {
        this.ingredient9 = ingredient9;
    }

    public Ingredient getIngredient10() {
        return ingredient10;
    }

    public void setIngredient10(Ingredient ingredient10) {
        this.ingredient10 = ingredient10;
    }
}
