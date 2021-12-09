SELECT special_pizza.pizza_id, pizza_name, pizza_description, pizza.price FROM special_pizza
FULL OUTER JOIN pizza ON pizza.pizza_id = special_pizza.pizza_id
FULL OUTER JOIN ingredient ON ingredient.ingredient_id = pizza_ingredient.ingredient_id;

SELECT ingredient_name, ingredient_type, ingredient.price, ingredient.quantity AS totalQuantity, pizza_ingredient.quantity FROM ingredient
INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id
WHERE pizza_id = 1

INNER JOIN ingredient ON ingredient.ingredient_id = pizza_ingredient.ingredient_id;

SELECT special_pizza.pizza_id, pizza_name, pizza_description, pizza.price FROM special_pizza
FULL OUTER JOIN pizza ON pizza.pizza_id = special_pizza.pizza_id;

SELECT SUM(price) FROM ingredient
INNER JOIN pizza_ingredient ON pizza_ingredient.ingredient_id = ingredient.ingredient_id
WHERE pizza_id = ?;

UPDATE pizza SET price = 0;

SELECT orders.order_id, orders.first_name, orders.last_name, orders.phone_number, orders.email, orders.order_total, orders.delivery, orders.completed, orders.order_date, orders.address_line_1, orders.address_state, orders.address_city, orders.address_zip_code
FROM order_items
FULL OUTER JOIN orders
ON order_items.order_id = orders.order_id
FULL OUTER JOIN menu
ON order_items.menu_item_id = menu.item_id
WHERE orders.completed = FALSE;

SELECT menu.item_name
FROM order_items
FULL OUTER JOIN menu
ON order_items.menu_item_id = menu.item_id
WHERE order_items.order_id = ?;

SELECT custom_pizza.pizza_id
FROM order_items
FULL OUTER JOIN custom_pizza
ON order_items.custom_pizza_id = custom_pizza.pizza_id;
WHERE order_items.order_id = ?;

--everything from ingredient where pizza-ingredient.pizza_id = pizza.pizza_id
