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

INSERT INTO order_items (order_items.order_id, item_type, menu_item_id, custom_pizza_id, item_size, quantity)
VALUES (1, 'Pizza', 1, 1, 'small', 1)
INNER JOIN menu ON order_items.menu_item_id = menu.item_id
INNER JOIN orders ON order_items.order_id = orders.order_id

SELECT item_name, total_quantity, price
FROM menu
WHERE item_category = 'Salad' OR item_category = 'Appetizer' OR item_category = 'Dessert' OR item_category = 'Drink';

SELECT ingredient_name, total_quantity, price
FROM ingredient;

INSERT INTO menu (item_name, item_description, item_category, price, total_quantity)
Values(1,1,'Salad',1,1) RETURNING item_id;

--everything from ingredient where pizza-ingredient.pizza_id = pizza.pizza_id
