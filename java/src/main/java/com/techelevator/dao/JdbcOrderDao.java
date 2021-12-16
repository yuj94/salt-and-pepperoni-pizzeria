package com.techelevator.dao;

import com.techelevator.model.CustomPizza;
import com.techelevator.model.Ingredient;
import com.techelevator.model.MenuItem;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;
import com.techelevator.model.Order;

import java.awt.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcOrderDao implements OrderDao {

    private JdbcTemplate jdbcTemplate;
    private static final String PIZZA_CATEGORY = "Pizza";
    private static final String MENU_ITEM = "Menu";
    private static final String CUSTOM_PIZZA = "Custom";
    private static final int SMALL_PIZZA = 12;
    private static final int MEDIUM_PIZZA = 16;
    private static final int LARGE_PIZZA = 18;


    public JdbcOrderDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAllPendingOrders() {
        List<Order> pendingOrderList = new ArrayList<>();

        String sql = "SELECT order_id, first_name, last_name, phone_number, email, order_total, delivery, completed, order_date, address_line_1, address_state, address_city, address_zip_code\n" +
                     "FROM orders WHERE orders.completed = FALSE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            pendingOrderList.add(mapRowToOrdersList(results));
        }

        return pendingOrderList;
    }

    @Override
    public List<Order> getAllHistoricalOrders() {
        List<Order> orderSearchList = new ArrayList<>();

        String sql = "SELECT order_id, first_name, last_name, phone_number, email, order_total, delivery, completed, order_date, address_line_1, address_state, address_city, address_zip_code\n" +
                "FROM orders WHERE orders.completed = TRUE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            orderSearchList.add(mapRowToOrdersList(results));
        }

        return orderSearchList;

    }

    @Override
    public List<Order> getIncompleteOrdersByOrderId() {
        List<Order> incompleteOrderList = new ArrayList<>();

        String sql = "SELECT orders.order_id\n" +
                     "FROM orders\n" +
                     "WHERE orders.completed = FALSE;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);

        while (results.next()) {
            incompleteOrderList.add(mapRowToIncompleteOrder(results));
        }

        return incompleteOrderList;
    }

    @Override
    public List<MenuItem> getMenuItemsByOrderId(int orderId) {
        List<MenuItem> orderMenuItemList = new ArrayList<>();

        String sql = "SELECT menu.item_name AS item_name, menu.item_id AS item_id, menu.price AS price, order_items.item_type AS item_type, order_items.item_size AS item_size, order_items.quantity AS quantity, menu.item_category AS item_category\n" +
                     "FROM order_items\n" +
                     "INNER JOIN menu\n" +
                     "ON order_items.menu_item_id = menu.item_id\n" +
                     "WHERE order_items.order_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);

        while (results.next()) {
            orderMenuItemList.add(mapRowToMenuItem(results));
        }

        return orderMenuItemList;
    }

    @Override
    public List<CustomPizza> getCustomPizzasByOrderId(int orderId) {
        List<CustomPizza> customPizzasList = new ArrayList<>();

        String sql = "SELECT custom_pizza.pizza_id AS item_id, custom_pizza.price AS price, order_items.item_type AS item_type, order_items.item_size AS item_size, order_items.quantity AS quantity\n" +
                     "FROM custom_pizza\n" +
                     "INNER JOIN order_items\n" +
                     "ON order_items.custom_pizza_id = custom_pizza.pizza_id\n" +
                     "WHERE order_items.order_id = ?;";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql, orderId);

        while (results.next()) {
            customPizzasList.add(mapRowToCustomPizza(results));
        }

        return customPizzasList;
    }

    @Override
    public int createOrder(Order order) {
        int orderId = 0;

        if (order.getIsDelivery()) {
            String sql = "INSERT INTO orders (first_name, last_name, address_line_1, address_state, address_city, address_zip_code, email, phone_number, delivery, credit_card_number, credit_card_exp_month, credit_card_exp_year, credit_card_ccv, order_total)\n" +
                    "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?) RETURNING order_id;";
            order.setOrderId(jdbcTemplate.queryForObject(sql, Integer.class, order.getFirstName(), order.getLastName(), order.getAddressLine(), order.getAddressState(), order.getAddressCity(), order.getAddressZipCode(), order.getEmail(), order.getPhoneNumber(), order.getIsDelivery(), order.getCreditCardNumber(), order.getCreditCardExpMonth(), order.getCreditCardExpYear(), order.getCreditCardCcv(), order.getOrderTotal()));
        } else {
            String sql = "INSERT INTO orders (first_name, last_name, email, phone_number, delivery, order_total)\n" +
                    "VALUES (?,?,?,?,?,?) RETURNING order_id;";
            order.setOrderId(jdbcTemplate.queryForObject(sql, Integer.class, order.getFirstName(), order.getLastName(), order.getEmail(), order.getPhoneNumber(), order.getIsDelivery(), order.getOrderTotal()));
        }

        orderId = order.getOrderId();

        setOrderItems(order);

        return orderId;
    }

    private void setOrderItems(Order order) {

        String menuItemSql = "INSERT INTO order_items (order_id, item_type, menu_item_id, item_size, quantity) " +
                "VALUES (?, ?, ?, ?, ?);";

        for (int i = 0; i < order.getMenuItems().size(); i++) {
            jdbcTemplate.update(menuItemSql, order.getOrderId(), MENU_ITEM, order.getMenuItems().get(i).getItemId(), order.getMenuItems().get(i).getItemSize(), order.getMenuItems().get(i).getOrderQuantity());
        }

        String customPizzaSql = "INSERT INTO order_items (order_id, item_type, custom_pizza_id, item_size, quantity) " +
                "VALUES (?, ?, ?, ?, ?);";

        for (int i = 0; i < order.getCustomPizza().size(); i++) {
            jdbcTemplate.update(customPizzaSql, order.getOrderId(), CUSTOM_PIZZA, order.getCustomPizza().get(i).getItemId(), order.getCustomPizza().get(i).getItemSize(), order.getCustomPizza().get(i).getOrderQuantity());
        }

        updateQuantities(order);

    }

    private void updateQuantities(Order order) {
        for (int i = 0; i < order.getMenuItems().size(); i++) {
            if (order.getMenuItems().get(i).getItemCategory().equals(PIZZA_CATEGORY)) {
                for (int n = 0; n < order.getMenuItems().get(i).getIngredientList().size(); n++) {
                    String sql = "UPDATE ingredient SET total_quantity = total_quantity - ? WHERE ingredient_id = ?";

                    if (order.getMenuItems().get(i).getItemSize() == SMALL_PIZZA){
                        jdbcTemplate.update(sql, order.getMenuItems().get(i).getOrderQuantity(), order.getMenuItems().get(i).getIngredientList().get(n).getIngredientId());
                    } else if (order.getMenuItems().get(i).getItemSize() == MEDIUM_PIZZA) {
                        jdbcTemplate.update(sql, order.getMenuItems().get(i).getOrderQuantity() * 1.3, order.getMenuItems().get(i).getIngredientList().get(n).getIngredientId());
                    } else if (order.getMenuItems().get(i).getItemSize() == LARGE_PIZZA) {
                        jdbcTemplate.update(sql, order.getMenuItems().get(i).getOrderQuantity() * 1.5, order.getMenuItems().get(i).getIngredientList().get(n).getIngredientId());
                    }
                }
            } else {
                String sql = "UPDATE menu SET total_quantity = total_quantity - ? WHERE item_id = ?";

                jdbcTemplate.update(sql, order.getMenuItems().get(i).getOrderQuantity(), order.getMenuItems().get(i).getItemId());
            }
        }

        for (int i = 0; i < order.getCustomPizza().size(); i++) {
            for (int n = 0; n < order.getCustomPizza().get(i).getIngredients().size(); n++) {
                String sql = "UPDATE ingredient SET total_quantity = total_quantity - ? WHERE ingredient_id = ?";

                if (order.getCustomPizza().get(i).getItemSize() == SMALL_PIZZA){
                    jdbcTemplate.update(sql, order.getCustomPizza().get(i).getOrderQuantity(), order.getCustomPizza().get(i).getIngredients().get(n).getIngredientId());
                } else if (order.getCustomPizza().get(i).getItemSize() == MEDIUM_PIZZA) {
                    jdbcTemplate.update(sql, order.getCustomPizza().get(i).getOrderQuantity() * 1.3, order.getCustomPizza().get(i).getIngredients().get(n).getIngredientId());
                } else if (order.getCustomPizza().get(i).getItemSize() == LARGE_PIZZA) {
                    jdbcTemplate.update(sql, order.getCustomPizza().get(i).getOrderQuantity() * 1.5, order.getCustomPizza().get(i).getIngredients().get(n).getIngredientId());
                }
            }
        }
    }

    private String getTimeElapsed(Order order) {
        String timeElapsed = "";
        if (!order.getIsCompleted()) {
            String sql = "SELECT (now() - order_date) AS time_elapsed FROM orders WHERE order_id = ?;";

            timeElapsed = jdbcTemplate.queryForObject(sql, String.class, order.getOrderId());
        } else {
            String sql = "SELECT (completed_date - order_date) AS time_elapsed FROM orders WHERE order_id = ?;";

            timeElapsed = jdbcTemplate.queryForObject(sql, String.class, order.getOrderId());
        }
        return timeElapsed;
    }

    @Override
    public void setOrderToComplete(int orderId){
        String sql ="UPDATE orders SET completed = TRUE, completed_date = now() WHERE order_id = ?;";

        jdbcTemplate.update(sql, orderId);
    }

    @Override
    public void setOrderToNotComplete(int orderId){
        String sql = "UPDATE orders SET completed = FALSE WHERE order_id = ?;";

        jdbcTemplate.update(sql, orderId);
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
        order.setIsDelivery(rowSet.getBoolean("delivery"));
        order.setIsCompleted(rowSet.getBoolean("completed"));
        order.setAddressLine(rowSet.getString("address_line_1"));
        order.setAddressState(rowSet.getString("address_state"));
        order.setAddressCity(rowSet.getString("address_city"));
        order.setAddressZipCode(rowSet.getInt("address_zip_code"));
        order.setTimeElapsed(getTimeElapsed(order));
        order.setCustomPizza(getCustomPizzasByOrderId(order.getOrderId()));
        order.setMenuItems(getMenuItemsByOrderId(order.getOrderId()));

        return order;
    }

    private Order mapRowToIncompleteOrder(SqlRowSet rowSet) {
        Order order = new Order();

        order.setOrderId(rowSet.getInt("order_id"));

        return order;
    }

    private CustomPizza mapRowToCustomPizza(SqlRowSet rowSet) {
        CustomPizza customPizza = new CustomPizza();

        customPizza.setPizzaId(rowSet.getInt("item_id"));
        customPizza.setPrice(rowSet.getBigDecimal("price"));
        customPizza.setItemType(rowSet.getString("item_type"));
        customPizza.setItemSize(rowSet.getInt("item_size"));
        customPizza.setOrderQuantity(rowSet.getInt("quantity"));

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
        menuItem.setItemType(rowSet.getString("item_type"));
        menuItem.setItemCategory(rowSet.getString("item_category"));
        menuItem.setItemName(rowSet.getString("item_name"));
        menuItem.setOrderQuantity(rowSet.getDouble("quantity"));
        menuItem.setItemSize(rowSet.getInt("item_size"));

        return menuItem;
    }
}


