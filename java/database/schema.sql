BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS pizza_ingredient;
DROP TABLE IF EXISTS special_pizza;
DROP TABLE IF EXISTS pizza;
DROP TABLE IF EXISTS ingredient;

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
        ingredient_id serial,
	ingredient_name varchar(32) NOT NULL UNIQUE,
	ingredient_type varchar(32) NOT NULL,
	price decimal (3, 2),
	quantity decimal (6, 2),

	CONSTRAINT PK_ingredient PRIMARY KEY (ingredient_id)
);

INSERT INTO ingredient (ingredient_name, ingredient_type) 
VALUES ('Dough', 'Dough'),
           ('Pepperoni', 'Meat'),
           ('Sweet Sausage', 'Meat'),
           ('Grilled Chicken', 'Meat'),
           ('Spicy Italian Sausage', 'Meat'),
           ('Bacon', 'Meat'),
           ('Ham', 'Meat'),
           ('Prosciutto', 'Meat'),
           ('Meatballs', 'Meat'),
           ('Salami', 'Meat'),
           ('Beef', 'Meat'),
           ('Philly Steak', 'Meat'),
           ('Anchovies', 'Meat'),
           ('Green Peppers', 'Veggies'),
           ('Jalepeno Peppers', 'Veggies'),
           ('Mushrooms', 'Veggies'),
           ('Roma Tomatoes', 'Veggies'),
           ('Black Olives', 'Veggies'),
           ('Capers', 'Veggies'),
           ('Basil', 'Veggies'),
           ('Green Olives', 'Veggies'),
           ('Pineapple', 'Veggies'),
           ('Fresh Spinach', 'Veggies'),
           ('Banana Peppers', 'Veggies'),
           ('Onions', 'Veggies'),
           ('Garlic', 'Veggies'),
           ('Sun Dried Tomatoes', 'Veggies'),
           ('Artichoke Hearts', 'Veggies'),
           ('Parmesan Romano', 'Cheese'),
           ('3-Cheese Blend', 'Cheese'),
           ('Cheddar', 'Cheese'),
           ('Asiago', 'Cheese'),
           ('Ricotta Cheese', 'Cheese'),
           ('Mozzarella', 'Cheese'),
           ('Pepperjack', 'Cheese'),
           ('American', 'Cheese'),
           ('Olive Oil', 'Sauce'),
           ('Tomato Base', 'Sauce'),
           ('Basil Pesto', 'Sauce'),
           ('BBQ Sauce', 'Sauce'),
           ('Ranch', 'Sauce'),
           ('Buffalo', 'Sauce'),
           ('Alfredo', 'Sauce');

           
UPDATE ingredient SET price = 8.00
WHERE ingredient_type = 'Dough';

UPDATE ingredient SET price = 1.00
WHERE ingredient_type = 'Sauce';

UPDATE ingredient SET price = 1.00
WHERE ingredient_type = 'Cheese';

UPDATE ingredient SET price = .80
WHERE ingredient_type = 'Veggies';

UPDATE ingredient SET price = 1.50
WHERE ingredient_type = 'Meat';
           
CREATE TABLE pizza (
	pizza_id serial,
	pizza_type varchar(12) NOT NULL CHECK (pizza_type = 'Custom' OR pizza_type = 'Specialty'),
        price decimal (4, 2),

	CONSTRAINT PK_pizza PRIMARY KEY (pizza_id)
);

INSERT INTO pizza (pizza_type)
VALUES ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty'),
       ('Specialty');

CREATE TABLE special_pizza (
	pizza_id int,
	pizza_name varchar(64) NOT NULL,
	pizza_description varchar(512) NOT NULL,

	CONSTRAINT PK_custom_pizza PRIMARY KEY (pizza_id),
	CONSTRAINT FK_custom_pizza_id FOREIGN KEY (pizza_id) REFERENCES pizza(pizza_id)
);

INSERT INTO special_pizza (pizza_id, pizza_name, pizza_description)
VALUES (1, 'Pepperoni Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with pepperoni, this is one of our most popular pizzas.'),
       (2, 'Sausage Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with sausage, this is our clasic Italian sausage pizza.');

INSERT INTO special_pizza (pizza_id, pizza_name, pizza_description)
VALUES (3, '3-Cheese Pizza', 'Our base pizza with rich tomato sauce base and a delightful 3-cheese blend.');

INSERT INTO special_pizza (pizza_id, pizza_name, pizza_description)
VALUES (4, 'The Works Pizza', 'It is everything you want on a pizza and then some with pepperoni, Canadian bacon, spicy Italian sausage, fresh-cut onions, crisp green peppers, mushrooms, ripe black olives, and real cheese made from mozzarella.'),
       (5, 'Fresh Spinach & Tomato Alfredo Pizza', 'Crafted with fresh cream and Parmesan and Romano cheeses, topped with fresh baby spinach, fresh-cut Roma tomatoes, sun-dried tomatoes, crisp green peppers, mushrooms, ripe black olives, real cheese made from mozzarella, plus classic Italian seasoning for an extra dash of flavor.'),
       (6, 'The Meats Pizza', 'Meat lovers dreams come true with this pizza. We start with a tomato base, pack on all the hearty meats (pepperoni, sweet sausage, spicy Italian sausage, salami, and meatballs), and top it off with some onions and black olives.'),
       (7, 'Zesty Italian Trio Pizza', 'A tasty trio of zesty Italian sausage, salami, and pepperoni, plus banana peppers, green olives, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough. Sprinkled with classic Italian seasoning for an extra dash of flavor.'),
       (8, 'Garden Fresh Pizza', 'All your favorite veggies together on a delightfully delicious pizza. Loaded with crisp green peppers, fresh-cut onions, mushrooms, ripe black and green olives, vine-ripened Roma tomatoes, and real cheese made from mozzarella.'),
       (9, 'Super Hawaiian Pizza', 'Although some find it controversial, we believe pineapple belongs on pizza. We take it to the next level by combining sweet, juicy pineapple tidbits, julienne-cut Canadian bacon, onions, banana peppers, hickory-smoked bacon, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough.'),
       (10, 'Meatball Pepperoni Pizza', 'Savory meatballs and pepperoni make for a tasty combination, especially when we add onions, black olives, sun-dried tomatoes, a three-cheese blend, and real cheese made from mozzarella on our original crust, then sprinkle it all with classic Italian seasoning for an extra dash of flavor.');

INSERT INTO special_pizza (pizza_id, pizza_name, pizza_description)    
VALUES (11, 'BBQ Chicken Bacon Ranch Pizza', 'We had you at bacon, right? It gets even better. Ranch dresssing base with a smoky Southern-style BBQ sauce add a tangy twist to this irresistible pizza topped with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella. Round one up today.'),
       (12, 'Fiery Buffalo Chicken Pizza', 'We are bringing the heat with more of the bold buffalo flavor you love. Our original crust is covered in ranch and a new buffalo sauce with a hint of buttery richness and a tangy, craveable kick. Piled high with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella.'), 
       (13, 'Pesto Pizza', 'Hand sliced mozzarella with spinach, onion, and olive oil and basil pesto sauce drizzle. Topped with our signature cheese and spice blend.');

INSERT INTO special_pizza (pizza_id, pizza_name, pizza_description)    
VALUES (14, 'Margherita Pizza', 'Traditional margherita pizza with our extra famous sauce, fresh basil, and mozzarella.');

CREATE TABLE pizza_ingredient (
        pizza_id int NOT NULL,
        ingredient_id int NOT NULL,
        quantity int NOT NULL CHECK (quantity >= 1),
        
        CONSTRAINT PK_pizza_ingredient PRIMARY KEY (pizza_id, ingredient_id, quantity),
        CONSTRAINT FK_pizza_ingredient_pizza_id FOREIGN KEY (pizza_id) REFERENCES pizza(pizza_id),
        CONSTRAINT FK_pizza_ingredient_name FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id)
);

INSERT INTO pizza_ingredient (pizza_id, ingredient_id, quantity)
VALUES (1, 1, 1),
       (1, 38, 1),
       (1, 34, 1),
       (1, 2, 1),
       (2, 1, 1),
       (2, 38, 1),
       (2, 34, 1),
       (2, 5, 1),
       (3, 1, 1),
       (3, 38, 1),
       (3, 30, 1),
       (4, 1, 1),
       (4, 2, 1),
       (4, 34, 1),
       (4, 38, 1),
       (4, 5, 1),
       (4, 25, 1),
       (4, 14, 1),
       (4, 16, 1),
       (4, 18, 1),
       (5, 1, 1),
       (5, 43, 1),
       (5, 29, 1),
       (5, 23, 1),
       (5, 17, 1),
       (5, 27, 1),
       (5, 14, 1),
       (5, 16, 1),
       (5, 18, 1),
       (6, 1, 1),
       (6, 2,1),
       (6, 3, 1),
       (6, 5, 1),
       (6, 38, 1),
       (6, 10, 1),
       (6, 9, 1),
       (6, 25, 1),
       (6, 18, 1),
       (7, 1, 1),
       (7, 5, 1),
       (7, 10, 1),
       (7, 2, 1),
       (7, 24, 1),
       (7, 30, 1),
       (7, 38, 1),
       (7, 34, 1),
       (7, 21, 1),
       (8, 1, 1),
       (8, 34, 1),
       (8, 38, 1),
       (8, 14, 1),
       (8, 25, 1),
       (8, 16, 1),
       (8, 17, 1),
       (8, 18, 1),
       (8, 21, 1),
       (9, 1, 1),
       (9, 6, 1),
       (9, 7, 1),
       (9, 38, 1),
       (9, 30, 1),
       (9, 34, 1),
       (9, 22, 1),
       (9, 25, 1),
       (9, 24, 1),
       (10, 1, 1),
       (10, 30, 1),
       (10, 34, 1),
       (10, 2, 1),
       (10, 38, 1),
       (10, 9, 1),
       (10, 18, 1),
       (10, 25, 1),
       (10, 27, 1),
       (11, 1, 1),
       (11, 40, 1),
       (11, 41, 1),
       (11, 4, 1),
       (11, 6, 1),
       (11, 25, 1),
       (11, 34, 1),
       (12, 1, 1),
       (12, 42, 1),
       (12, 41, 1),
       (12, 4, 1),
       (12, 6, 1),
       (12, 25, 1),
       (12, 34, 1),
       (13, 1, 1),
       (13, 37, 1),
       (13, 39, 1),
       (13, 34, 1),
       (13, 23, 1),
       (13, 25, 1),
       (13, 20, 1),
       (14, 1, 1),
       (14, 38, 1),
       (14, 37, 1),
       (14, 17, 1),
       (14, 20, 1);

CREATE TABLE orders (
        order_id serial,
        order_date timestamp DEFAULT now(),
        completed_date timestamp,
        first_name varchar(32) NOT NULL,
        last_name varchar(32) NOT NULL,
        address_line_1 varchar(64),
        address_state varchar(2),
        address_city varchar(64),
        address_zip_code decimal(5, 0),
        email varchar(64) UNIQUE CHECK (email LIKE '%@%.%') NOT NULL,
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
        item_id int NOT NULL,
        item_size varchar(12),
        quantity int NOT NULL,
        
        CONSTRAINT FK_order_items_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id),
        CONSTRAINT FK_order_items_item_id FOREIGN KEY (item_id) REFERENCES pizza(pizza_id)
);


COMMIT TRANSACTION;
