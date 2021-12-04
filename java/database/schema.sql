BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;

CREATE SEQUENCE seq_user_id
  INCREMENT BY 1
  NO MAXVALUE
  NO MINVALUE
  CACHE 1;


CREATE TABLE users (
	user_id int DEFAULT nextval('seq_user_id'::regclass) NOT NULL,
	username varchar(50) NOT NULL,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

INSERT INTO users (username,password_hash,role) VALUES ('user','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_USER');
INSERT INTO users (username,password_hash,role) VALUES ('admin','$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC','ROLE_ADMIN');

CREATE TABLE ingredient (
	ingredient_name varchar(32) NOT NULL UNIQUE,
	ingredient_type varchar(32) NOT NULL,
	price decimal (3, 2) NOT NULL,
	quantity decimal (6, 2),

	CONSTRAINT PK_ingredient PRIMARY KEY (ingredient_name)
);

INSERT INTO ingredient (ingredient_name, ingredient_type, price) 
VALUES ('Pepperoni', 'Meat', 1.5),
           ('Sweet Sausage', 'Meat', 1.5),
           ('Grilled Chicken', 'Meat', 1.5),
           ('Spicy Italian Sausage', 'Meat', 1.5),
           ('Bacon', 'Meat', 1.5),
           ('Ham', 'Meat', 1.5),
           ('Prosciutto', 'Meat', 1.5),
           ('Meatballs', 'Meat', 1.5),
           ('Salami', 'Meat', 1.5),
           ('Beef', 'Meat', 1.5),
           ('Philly Steak', 'Meat', 1.5),
           ('Anchovies', 'Meat', 1.5),
           ('Green Peppers', 'Veggies', .75),
           ('Jalepeno Peppers', 'Veggies', .75),
           ('Mushrooms', 'Veggies', .75),
           ('Roma Tomatoes', 'Veggies', .75),
           ('Black Olives', 'Veggies', .75),
           ('Capers', 'Veggies', .75),
           ('Basil', 'Veggies', .75),
           ('Green Olives', 'Veggies', .75),
           ('Pineapple', 'Veggies', .75),
           ('Fresh Spinach', 'Veggies', .75),
           ('Banana Peppers', 'Veggies', .75),
           ('Onions', 'Veggies', .75),
           ('Garlic', 'Veggies', .75),
           ('Sun Dried Tomatoes', 'Veggies', .75),
           ('Artichoke Hearts', 'Veggies', .75),
           ('Parmesan Romano', 'Cheese', 1.25),
           ('3-Cheese Blend', 'Cheese', 1.25),
           ('Cheddar', 'Cheese', 1.25),
           ('Asiago', 'Cheese', 1.25),
           ('Ricotta Cheese', 'Cheese', 1.25),
           ('Mozzarella', 'Cheese', 1.25),
           ('Pepperjack', 'Cheese', 1.25),
           ('American', 'Cheese', 1.25),
           ('Olive Oil', 'Sauce', .80),
           ('Tomato Base', 'Sauce', .80),
           ('Basil Pesto', 'Sauce', .80),
           ('BBQ Sauce', 'Sauce', .80),
           ('Ranch', 'Sauce', .80),
           ('Buffalo', 'Sauce', .80),
           ('Alfredo', 'Sauce', .80),
           ('Dough', 'Dough', 3.50);
           
CREATE TABLE pizza (
	pizza_id serial,
	pizza_name varchar(64) NOT NULL,
	pizza_description varchar(512) NOT NULL,
	price decimal (4, 2),
	ingredient_1 varchar(32),
	ingredient_2 varchar(32),
	ingredient_3 varchar(32),
	ingredient_4 varchar(32),
	ingredient_5 varchar(32),
	ingredient_6 varchar(32),
	ingredient_7 varchar(32),
	ingredient_8 varchar(32),
	ingredient_9 varchar(32),
	ingredient_10 varchar(32),

	CONSTRAINT PK_pizza PRIMARY KEY (pizza_id),
	CONSTRAINT FK_pizza_ing_1 FOREIGN KEY (ingredient_1) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_2 FOREIGN KEY (ingredient_2) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_3 FOREIGN KEY (ingredient_3) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_4 FOREIGN KEY (ingredient_4) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_5 FOREIGN KEY (ingredient_5) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_6 FOREIGN KEY (ingredient_6) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_7 FOREIGN KEY (ingredient_7) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_8 FOREIGN KEY (ingredient_8) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_9 FOREIGN KEY (ingredient_9) REFERENCES ingredient(ingredient_name),
	CONSTRAINT FK_pizza_ing_10 FOREIGN KEY (ingredient_10) REFERENCES ingredient(ingredient_name)
);

INSERT INTO pizza (pizza_name, pizza_description, price, ingredient_1, ingredient_2, ingredient_3, ingredient_4)
VALUES ('Pepperoni Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with pepperoni, this is one of our most popular pizzas.', 13.50, 'Dough', 'Tomato Base', 'Mozzarella', 'Pepperoni'),
       ('Sausage Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with sausage, this is our clasic Italian sausage pizza.', 13.50, 'Dough', 'Tomato Base', 'Mozzarella', 'Spicy Italian Sausage');

INSERT INTO pizza (pizza_name, pizza_description, price, ingredient_1, ingredient_2, ingredient_3)
VALUES ('3-Cheese Pizza', 'Our base pizza with rich tomato sauce base and a delightful 3-cheese blend.', 11.50, 'Dough', 'Tomato Base', '3-Cheese Blend');

INSERT INTO pizza (pizza_name, pizza_description, price, ingredient_1, ingredient_2, ingredient_3, ingredient_4, ingredient_5, ingredient_6, ingredient_7, ingredient_8, ingredient_9)
VALUES ('The Works Pizza', 'It is everything you want on a pizza – and then some with pepperoni, Canadian bacon, spicy Italian sausage, fresh-cut onions, crisp green peppers, mushrooms, ripe black olives, and real cheese made from mozzarella.', 17.50, 'Dough', 'Pepperoni', 'Mozzarella', 'Tomato Base', 'Spicy Italian Sausage', 'Onions', 'Green Peppers', 'Mushrooms', 'Black Olives'),
       ('Fresh Spinach & Tomato Alfredo Pizza', 'Crafted with fresh cream and Parmesan and Romano cheeses, topped with fresh baby spinach, fresh-cut Roma tomatoes, sun-dried tomatoes, crisp green peppers, mushrooms, ripe black olives, real cheese made from mozzarella, plus classic Italian seasoning for an extra dash of flavor.', 17.50, 'Dough', 'Alfredo', 'Parmesan Romano', 'Fresh Spinach', 'Roma Tomatoes', 'Sun Dried Tomatoes', 'Green Peppers', 'Mushrooms', 'Black Olives'),
       ('The Meats Pizza', 'Meat lovers dreams come true with this pizza. We start with a tomato base, pack on all the hearty meats (pepperoni, sweet sausage, spicy Italian sausage, salami, and meatballs), and top it off with some onions and black olives.', 17.50, 'Dough', 'Pepperoni', 'Sweet Sausage', 'Spicy Italian Sausage', 'Tomato Base', 'Salami', 'Meatballs', 'Onions', 'Black Olives'),
       ('Zesty Italian Trio Pizza', 'A tasty trio of zesty Italian sausage, salami, and pepperoni, plus banana peppers, green olives, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough. Sprinkled with classic Italian seasoning for an extra dash of flavor.', 17.50, 'Dough', 'Spicy Italian Sausage', 'Salami', 'Pepperoni', 'Banana Peppers', '3-Cheese Blend', 'Tomato Base', 'Mozzarella', 'Green Olives'),
       ('Garden Fresh Pizza', 'All your favorite veggies together on a delightfully delicious pizza. Loaded with crisp green peppers, fresh-cut onions, mushrooms, ripe black and green olives, vine-ripened Roma tomatoes, and real cheese made from mozzarella.', 17.50, 'Dough', 'Mozzarella', 'Tomato Base', 'Green Peppers', 'Onions', 'Mushrooms', 'Roma Tomatoes', 'Black Olives', 'Green Olives'),
       ('Super Hawaiian Pizza', 'Although some find it controversial, we believe pineapple belongs on pizza. We take it to the next level by combining sweet, juicy pineapple tidbits, julienne-cut Canadian bacon, onions, banana peppers, hickory-smoked bacon, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough.', 17.50, 'Dough', 'Bacon', 'Ham', 'Tomato Base', '3-Cheese Blend', 'Mozzarella', 'Pineapple', 'Onions', 'Banana Peppers'),
       ('Meatball Pepperoni Pizza', 'Savory meatballs and pepperoni make for a tasty combination, especially when we add onions, black olives, sun-dried tomatoes, a three-cheese blend, and real cheese made from mozzarella on our original crust, then sprinkle it all with classic Italian seasoning for an extra dash of flavor.', 17.50, 'Dough', '3-Cheese Blend', 'Mozzarella', 'Pepperoni', 'Tomato Base', 'Meatballs', 'Black Olives', 'Onions', 'Sun Dried Tomatoes');

INSERT INTO pizza (pizza_name, pizza_description, price, ingredient_1, ingredient_2, ingredient_3, ingredient_4, ingredient_5, ingredient_6, ingredient_7)    
VALUES ('BBQ Chicken Bacon Ranch Pizza', 'We had you at bacon, right? It gets even better. Ranch dresssing base with a smoky Southern-style BBQ sauce add a tangy twist to this irresistible pizza topped with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella. Round one up today.', 17.50, 'Dough', 'BBQ Sauce', 'Ranch', 'Grilled Chicken', 'Bacon', 'Onions', 'Mozzarella'),
       ('Fiery Buffalo Chicken Pizza', 'We are bringing the heat with more of the bold buffalo flavor you love. Our original crust is covered in ranch and a new buffalo sauce with a hint of buttery richness and a tangy, craveable kick. Piled high with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella.', 17.50, 'Dough', 'Buffalo', 'Ranch', 'Grilled Chicken', 'Bacon', 'Onions', 'Mozzarella'), 
       ('Pesto Pizza', 'Hand sliced mozzarella with spinach, onion, and olive oil and basil pesto sauce drizzle. Topped with our signature cheese and spice blend.', 14.50, 'Dough', 'Olive Oil', 'Basil Pesto', 'Mozzarella', 'Fresh Spinach', 'Onions', 'Basil');

INSERT INTO pizza (pizza_name, pizza_description, price, ingredient_1, ingredient_2, ingredient_3, ingredient_4, ingredient_5)    
VALUES ('Margherita Pizza', 'Traditional margherita pizza with our extra famous sauce, fresh basil, and mozzarella.', 14.50, 'Dough', 'Tomato Base', 'Olive Oil', 'Roma Tomatoes', 'Basil');

CREATE TABLE orders (
        order_id serial,
        first_name varchar(32) NOT NULL,
        last_name varchar(32) NOT NULL,
        address_line_1 varchar(64),
        address_state varchar(2),
        address_city varchar(64),
        address_zip_code decimal(5, 0),
        email varchar(64) CHECK (email LIKE '%@%.%'),
        phone_number varchar(10) NOT NULL,
        delivery boolean DEFAULT false,
        completed boolean DEFAULT false,
        credit_card_number decimal(16, 0) NOT NULL CHECK (credit_card_number > 1000000000000000),
        credit_card_exp_month decimal(2, 0) NOT NULL CHECK (credit_card_exp_month >= 1 AND credit_card_exp_month <= 12),
        credit_card_exp_year decimal(2, 0) NOT NULL CHECK (credit_card_exp_year >= 20 AND credit_card_exp_year <= 30),
        credit_card_ccv decimal(4, 0) NOT NULL,
        order_total decimal(6, 2) DEFAULT 0,
        
        CONSTRAINT PK_orders PRIMARY KEY (order_id)
);

CREATE TABLE order_items (
        order_id int NOT NULL,
        item_type varchar(12) NOT NULL,
        item_size varchar(12),
        quantity int NOT NULL,
        
        CONSTRAINT FK_order_items FOREIGN KEY (order_id) REFERENCES orders(order_id)
);


COMMIT TRANSACTION;
