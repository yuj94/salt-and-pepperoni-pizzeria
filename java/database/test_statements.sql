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


--everything from ingredient where pizza-ingredient.pizza_id = pizza.pizza_id