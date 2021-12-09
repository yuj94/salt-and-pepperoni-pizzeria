package com.techelevator.dao;

import com.techelevator.model.CustomPizza;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements OrderDao {

    private JdbcTemplate jdbcTemplate;
    private static final String PIZZA_CATEGORY = "Pizza";

    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAllPendingOrders() {
        List<Order> pendingOrderList = new ArrayList<>();

        String sql = "SELECT orders.order_id, orders.first_name, orders.last_name, orders.phone_number, orders.email, orders.order_total, orders.delivery, orders.completed, orders.order_date, orders.address_line_1, orders.address_state, orders.address_city, orders.address_zip_code\n" +
                     "FROM order_items\n" +
                     "FULL OUTER JOIN orders\n" +
                     "ON order_items.order_id = orders.order_id\n" +
                     "FULL OUTER JOIN menu\n" +
                     "ON order_items.menu_item_id = menu.item_id\n" +
                     "WHERE orders.completed = FALSE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            pendingOrderList.add(mapRowToOrdersList(results));
        }

        return pendingOrderList;
    }

    @Override
    public List<Order> getAllOrderHistory() {
        List<Order> orderHistoryList = new ArrayList<>();

        String sql = "SELECT orders.order_id, orders.first_name, orders.last_name, orders.phone_number, orders.email, orders.order_total, orders.delivery, orders.completed, orders.order_date, orders.address_line_1, orders.address_state, orders.address_city, orders.address_zip_code\n" +
                "FROM order_items\n" +
                "FULL OUTER JOIN orders\n" +
                "ON order_items.order_id = orders.order_id\n" +
                "FULL OUTER JOIN menu\n" +
                "ON order_items.menu_item_id = menu.item_id\n" +
                "WHERE orders.completed = TRUE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            orderHistoryList.add(mapRowToOrdersList(results));
        }

        return orderHistoryList;

    }

    @Override
    public List<Order> getUncompletedOrdersByOrderId() {
        List<Order> uncompletedOrderList = new ArrayList<>();

        String sql = "SELECT orders.order_id\n" +
                     "FROM orders\n" +
                     "WHERE orders.completed = FALSE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            uncompletedOrderList.add(mapRowToUncompletedOrder(results));
        }

        return uncompletedOrderList;
    }

    @Override
    public List<MenuItem> getMenuItemsByOrderId(int orderId) {
        List<MenuItem> orderMenuItemList = new ArrayList<>();

        String sql = "SELECT menu.item_name, menu.price\n" +
                     "FROM order_items\n" +
                     "FULL OUTER JOIN menu\n" +
                     "ON order_items.menu_item_id = menu.item_id\n" +
                     "WHERE order_items.order_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            orderMenuItemList.add(mapRowToMenuItem(results));
        }

        return orderMenuItemList;
    }

    @Override
    public List<CustomPizza> getCustomPizzasByOrderId(int orderId) {
        List<CustomPizza> customPizzasList = new ArrayList<>();

        String sql = "SELECT *\n" +
                     "FROM custom_pizza\n" +
                     "FULL OUTER JOIN order_items\n" +
                     "ON order_items.custom_pizza_id = custom_pizza.pizza_id\n" +
                     "WHERE order_items.order_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            customPizzasList.add(mapRowToCustomPizza(results));
        }

        return customPizzasList;
    }

    @Override
    public List<Order> getCart() {
        List<Order> pendingOrderList = new ArrayList<>();

        // String sql =
        return pendingOrderList;
    }



    //everything below is an import from JdbcMenuDao

    private List<Ingredient> getMenuPizzaIngredients(int pizzaId) {
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
        String sql = "SELECT SUM(price) FROM ingredient\n" +
                     "INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id\n" +
                     "WHERE pizza_id = ?;";

        return jdbcTemplate.queryForObject(sql, BigDecimal.class, pizza.getItemId());
    }

    private Order mapRowToOrdersList(SqlRowSet rowSet) {
        Order order = new Order();

        order.setOrderId(rowSet.getInt("order_id"));
        order.setFirstName(rowSet.getString("first_name"));
        order.setLastName(rowSet.getString("last_name"));
        order.setPhoneNumber(rowSet.getString("phone_number"));
        order.setEmail(rowSet.getString("email"));
        order.setOrderTotal(rowSet.getBigDecimal("order_total"));
        order.setDelivery(rowSet.getBoolean("delivery"));
        order.setCompleted(rowSet.getBoolean("completed"));
        order.setAddressLine(rowSet.getString("address_line_1"));
        order.setAddressState(rowSet.getString("address_state"));
        order.setAddressCity(rowSet.getString("address_city"));
        order.setAddressZipCode(rowSet.getInt("address_zip_code"));

        return order;
    }

    private Order mapRowToUncompletedOrder(SqlRowSet rowSet) {
        Order order = new Order();

        order.setOrderId(rowSet.getInt("order_id"));

        return order;
    }

    private CustomPizza mapRowToCustomPizza(SqlRowSet rowSet) {
        CustomPizza customPizza = new CustomPizza();

        customPizza.setPizzaId(rowSet.getInt("pizza_id"));
        customPizza.setPrice(rowSet.getBigDecimal("price"));

        return customPizza;
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

    private MenuItem mapRowToMenuItem(SqlRowSet rowSet) {
        MenuItem menuItem = new MenuItem();

        menuItem.setItemId(rowSet.getInt("item_id"));
        menuItem.setPrice(rowSet.getBigDecimal("price"));
        menuItem.setItemCategory(rowSet.getString("item_category"));
        menuItem.setItemName(rowSet.getString("item_name"));
        menuItem.setItemDescription(rowSet.getString("item_description"));
        menuItem.setTotalQuantity(rowSet.getDouble("total_quantity"));

        if (rowSet.getString("item_category").equals(PIZZA_CATEGORY)) {
            menuItem.setPrice(getMenuItemPrice(menuItem));
            menuItem.setIngredientList(getMenuPizzaIngredients(menuItem.getItemId()));
        }

        return menuItem;
    }
}


