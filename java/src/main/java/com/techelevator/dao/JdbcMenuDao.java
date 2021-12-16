package com.techelevator.dao;

import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import com.techelevator.model.CustomPizza;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcMenuDao implements MenuDao {

    private JdbcTemplate jdbcTemplate;
    private static final String PIZZA_CATEGORY = "Pizza";
    private static final int MAX_INGREDIENTS_ALLOWED = 10;

    public JdbcMenuDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<MenuItem> getAllMenuItems() {
        List<MenuItem> menuItemList = new ArrayList<>();

        String sql = "SELECT item_id, item_name, item_description, item_category, price, total_quantity, image_url FROM menu;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            menuItemList.add(mapRowToMenuItem(results));
        }

        return menuItemList;
    }

    @Override
    public List<Ingredient> getMenuPizzaIngredients(int pizzaId) {
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
    public CustomPizza getCustomPizza(int customPizzaId) {
        CustomPizza pizza = null;

        String sql = "SELECT pizza_id, price FROM pizza WHERE pizza_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, customPizzaId);

        pizza.setIngredientList(getMenuPizzaIngredients(pizza.getItemId()));

        return pizza;
    }

    @Override
    public int createCustomPizza(CustomPizza customPizza) {

        int customPizzaId = 0;

        if (customPizza.getIngredients().size() <= MAX_INGREDIENTS_ALLOWED) {
            String sql = "INSERT INTO custom_pizza (price) " +
                         "VALUES (?) RETURNING pizza_id;";

            customPizza.setPizzaId(jdbcTemplate.queryForObject(sql, Integer.class, customPizza.getPrice()));

            addIngredientsToPizzaIngredientTable(customPizza);

            customPizzaId = customPizza.getPizzaId();

        }

        return customPizzaId;
    }

    private void addIngredientsToPizzaIngredientTable(CustomPizza customPizza) {
        String sql = "INSERT INTO pizza_ingredient (custom_pizza_id, ingredient_id, quantity) " +
                     "VALUES (?, ?, ?);";

        for (int i = 0; i < customPizza.getIngredients().size(); i++) {
            jdbcTemplate.update(sql, customPizza.getPizzaId(), customPizza.getIngredients().get(i).getIngredientId(), customPizza.getIngredients().get(i).getOrderQuantity());
        }

    }

    private void setMenuItemPrice(MenuItem menuItem) {
        String sql = "UPDATE menu SET price = ? WHERE item_id = ?;";

        jdbcTemplate.update(sql, calculatePizzaPrice(menuItem), menuItem.getItemId());
    }

    private BigDecimal getMenuItemPrice(MenuItem menuItem) {
        setMenuItemPrice(menuItem);
        String sql = "SELECT price FROM menu WHERE item_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, menuItem.getItemId());
    }

    private BigDecimal calculatePizzaPrice(MenuItem pizza) {
        String sql = "SELECT SUM (price) FROM ingredient\n" +
                     "INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                     "WHERE pizza_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getItemId());
    }

//    private CustomPizza mapRowToCustomPizza(SqlRowSet rowSet) {
//        CustomPizza pizza = new CustomPizza();
//
//        pizza.setItemId(rowSet.getInt("item_id"));
//        pizza.setPrice(getPizzaPrice(pizza));
//        pizza.setItemCategory(rowSet.getString("item_category"));
//
//        return pizza;
//    }

    @Override
    public int createMenuItem(MenuItem menuItem) {
        String sql = "INSERT INTO menu (item_name, item_description, item_category, price, total_quantity)\n"+
                     "VALUES (?,?,?,?,?) RETURNING item_id;";
        int itemId = jdbcTemplate.queryForObject(sql, Integer.class, menuItem.getItemName(), menuItem.getItemDescription(), menuItem.getItemCategory(), menuItem.getPrice(), menuItem.getTotalQuantity());
        return itemId;
    }


    @Override
    public int updateIngredientQuantity (int ingredientId, Double quantity){
        String sql = "UPDATE ingredient\n" +
                     "SET total_quantity = ?\n" +
                     "WHERE ingredient_id = ?;";
        return jdbcTemplate.update(sql, quantity, ingredientId);
    }

    @Override
    public int updateIngredientPrice (int ingredientId, BigDecimal price){
        String sql = "UPDATE ingredient\n" +
                     "SET price = ?\n" +
                     "WHERE ingredient_id = ?";
        return jdbcTemplate.update(sql, price, ingredientId);
    }

    @Override
    public int updateMenuItemQuantity (int itemId, double quantity){
        String sql = "UPDATE menu\n" +
                     "SET total_quantity = ?\n" +
                     "WHERE item_id = ?;";
        return jdbcTemplate.update(sql, quantity, itemId);
    }

    @Override
    public int updateMenuItemPrice (int itemId, BigDecimal price){
        String sql = "UPDATE menu\n" +
                     "SET price = ?\n" +
                     "WHERE item_id = ?;";
        return jdbcTemplate.update(sql, price, itemId);
    }

    @Override
    public int addIngredient (Ingredient ingredient){
        String sql = "INSERT INTO ingredient (ingredient_name, ingredient_type, price, total_quantity)\n"+
                     "VALUES (?,?,?,?) RETURNING ingredient_id;";
        int ingredientId = jdbcTemplate.queryForObject(sql, Integer.class, ingredient.getIngredientName(), ingredient.getIngredientType(), ingredient.getPrice(), ingredient.getTotalQuantity());
        return ingredientId;
    }

    private Ingredient mapRowToIngredient(SqlRowSet rowSet) {
        Ingredient ingredient = new Ingredient();

        ingredient.setIngredientId(rowSet.getInt("ingredient_id"));
        ingredient.setIngredientName(rowSet.getString("ingredient_name"));
        ingredient.setIngredientType(rowSet.getString("ingredient_type"));
        ingredient.setPrice(rowSet.getBigDecimal("price"));
        ingredient.setTotalQuantity(rowSet.getDouble("total_quantity"));
        //ingredient.setOrderQuantity(rowSet.getDouble("order_quantity"));

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
        menuItem.setImageUrl(rowSet.getString("image_url"));

        if (rowSet.getString("item_category").equals(PIZZA_CATEGORY)) {
            menuItem.setPrice(getMenuItemPrice(menuItem));
            menuItem.setIngredientList(getMenuPizzaIngredients(menuItem.getItemId()));
        }

        return menuItem;
    }

}
