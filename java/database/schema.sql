BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS seq_user_id;
DROP TABLE IF EXISTS order_items;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS pizza_ingredient;
DROP TABLE IF EXISTS custom_pizza;
DROP TABLE IF EXISTS ingredient;
DROP TABLE IF EXISTS menu;




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


CREATE TABLE menu (
        item_id serial,
        item_name varchar(64) NOT NULL,
        item_description varchar(512),
        item_category varchar(32) NOT NULL, 
        price decimal (4,2),
        total_quantity decimal (6,2),
        image_url varchar(512),
        
        CONSTRAINT PK_menu PRIMARY KEY (item_id)
);

CREATE TABLE ingredient (
        ingredient_id serial,
	ingredient_name varchar(32) NOT NULL UNIQUE,
	ingredient_type varchar(32) NOT NULL,
	price decimal (3, 2),
	total_quantity decimal (6, 2),

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
           
CREATE TABLE custom_pizza (
	pizza_id serial,
        price decimal (4, 2),

	CONSTRAINT PK_custom_pizza PRIMARY KEY (pizza_id)
);

INSERT INTO menu (item_name, item_description, item_category, image_url)
VALUES ('Pepperoni Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with pepperoni, this is one of our most popular pizzas', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/pepperoni-slate-compressed.png'),
       ('Sausage Pizza', 'Starting with our signature pizza sauce, adding on real cheese made from mozzarella, and finishing off with sausage, this is our clasic Italian sausage pizza', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/sausage-slate-compressed.png'),
       ('3-Cheese Pizza', 'Our base pizza with rich tomato sauce base and a delightful 3-cheese blend', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/cheese-slate-compressed.png'),
       ('The Works Pizza', 'It is everything you want on a pizza and then some with pepperoni, Canadian bacon, spicy Italian sausage, fresh-cut onions, crisp green peppers, mushrooms, ripe black olives, and real cheese made from mozzarella', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/the-works-slate-compressed.png'),
       ('Fresh Spinach & Tomato Alfredo Pizza', 'Crafted with fresh cream and Parmesan and Romano cheeses, topped with fresh baby spinach, fresh-cut Roma tomatoes, sun-dried tomatoes, crisp green peppers, mushrooms, ripe black olives, real cheese made from mozzarella, plus classic Italian seasoning for an extra dash of flavor', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/fresh-spinach-tomato-compressed.png'),
       ('The Meats Pizza', 'Meat lovers dreams come true with this pizza. We start with a tomato base, pack on all the hearty meats (pepperoni, sweet sausage, spicy Italian sausage, salami, and meatballs), and top it off with some onions and black olives', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/the-meats-slate-compressed.png'),
       ('Zesty Italian Trio Pizza', 'A tasty trio of zesty Italian sausage, salami, and pepperoni, plus banana peppers, green olives, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough. Sprinkled with classic Italian seasoning for an extra dash of flavor', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/zesty-italian-trio-slate-compressed.png'),
       ('Garden Fresh Pizza', 'All your favorite veggies together on a delightfully delicious pizza. Loaded with crisp green peppers, fresh-cut onions, mushrooms, ripe black and green olives, vine-ripened Roma tomatoes, and real cheese made from mozzarella', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/garden-fresh-slate-compressed.png'),
       ('Super Hawaiian Pizza', 'Although some find it controversial, we believe pineapple belongs on pizza. We take it to the next level by combining sweet, juicy pineapple tidbits, julienne-cut Canadian bacon, onions, banana peppers, hickory-smoked bacon, a three-cheese blend, and real cheese made from mozzarella on our signature sauce and original fresh dough', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/super-hawaiian-slate-compressed.png'),
       ('Meatball Pepperoni Pizza', 'Savory meatballs and pepperoni make for a tasty combination, especially when we add onions, black olives, sun-dried tomatoes, a three-cheese blend, and real cheese made from mozzarella on our original crust, then sprinkle it all with classic Italian seasoning for an extra dash of flavor', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/meatball-pepp-slate-compressed.png'),
       ('BBQ Chicken Bacon Ranch Pizza', 'We had you at bacon, right? It gets even better. Ranch dresssing base with a smoky Southern-style BBQ sauce add a tangy twist to this irresistible pizza topped with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella. Round one up today', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/bbq-chicken-bacon-slate-compressed.png'),
       ('Fiery Buffalo Chicken Pizza', 'We are bringing the heat with more of the bold buffalo flavor you love. Our original crust is covered in ranch and a new buffalo sauce with a hint of buttery richness and a tangy, craveable kick. Piled high with grilled chicken, hickory-smoked bacon, fresh-cut onions, and real cheese made from mozzarella', 'Pizza', 'https://www.papajohns.com/static-assets/a/images/compressed/product/pizzas/fiery-buffalo-chicken-slate-compressed.png');
          
INSERT INTO menu (item_name, item_description, item_category, price, image_url)
VALUES ('Antipasto', 'Fresh mozzarella, oven roasted sweet red peppers, genoa salami, kalamata olives, green sicilian olives, drizzle of extra virgin olive oil, basil leaf garnish & bread', 'Appetizer', 13, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240dc2b7eContent_ContentFile_mobile_Antipasto_1170.png_companyCode_GRPI001_designId_1'),
       ('Bruschetta', 'Our bruschetta is served trio style', 'Appetizer', 12, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240df2c18Content_ContentFile_mobile_BruschettaTrio_1170.png_companyCode_GRPI001_designId_1'), 
       ('Caprese', 'Slices of fresh mozzarella, tomatoes, fresh basil, drizzled with extra virgin olive oil & fresh cracked black pepper', 'Appetizer', 10, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240eb4492Content_ContentFile_mobile_Caprese_1170.png_companyCode_GRPI001_designId_1'),
       ('Mediterranean Salad', 'Romaine lettuce, cucumbers, tomatoes, Kalamata olives, feta cheese, red onion served with house vinaigrette dressing on the side', 'Salad', 10, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240e1d690Content_ContentFile_MediterraneanSalad_1170.png_companyCode_GRPI001_designId_1'),
       ('Spinach Salad', 'Baby spinach, red onion, cherry tomatoes, bacon, crumbled Gorgonzola cheese served with balsamic vinaigrette dressing on the side', 'Salad', 11, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240e4a544Content_ContentFile_mobile_SpinachSalad_1170.png_companyCode_GRPI001_designId_1'),
       ('Caesar Salad', 'Romaine lettuce, imported Parmesan Cheese, coal oven baked croutons served with traditional caesar dressing on the side', 'Salad', 10, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240e6de38Content_ContentFile_mobile_CaesarSalad_1170.png_companyCode_GRPI001_designId_1'),
       ('House Salad', 'Romaine lettuce, red onion, cherry tomatoes, oven roasted sweet red peppers, mushrooms, Sicilian green olives served with house vinaigrette dressing on the side', 'Salad', 9, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240e98b78Content_ContentFile_mobile_HouseSalad_1170.png_companyCode_GRPI001_designId_1'),
       ('Lemon Arugula Salad', 'Arugula, shaved Italian cheese, coal oven baked croutons served with lemon vinaigrette dressing on the side and garnished with lemon wedges', 'Salad', 10, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4240ee462dContent_ContentFile_mobile_LemonArugulaSalad_1170.png_companyCode_GRPI001_designId_1'),
       ('Cannoli', 'Fried pastry dough filled with sweet ricotta cheese and chocolate chips', 'Dessert', 5, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b42412bc24aContent_ContentFile_mobile_Cannoli_1170.png_companyCode_GRPI001_designId_1'),
       ('Tiramisu', 'Espresso soaked ladyfingers, layered with sweet mascarpone. Finished with a dusting of cocoa powder', 'Dessert', 6, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b42412dc146Content_ContentFile_mobile_Tiramisu_1170.png_companyCode_GRPI001_designId_1'),
       ('Oreo Cheesecake', 'Traditional New York style cheesecake with crumbled Oreo cookies, on an Oreo cookie crust', 'Dessert', 6, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b4241347629Content_ContentFile_mobile_Oreo_1170.png_companyCode_GRPI001_designId_1'),
       ('New York Cheesecake', 'Traditional New York style cheesecake, on a sugar cookie crust', 'Dessert', 6, 'https://d1ouk4tp1vcuss.cloudfront.net/s3.amazonaws.com/ODNUploads/61b424131d849Content_ContentFile_mobile_NewYork_1170.png_companyCode_GRPI001_designId_1'),
       ('Coke', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/71XgryEbahL._SL1500_.jpg'),
       ('Diet Coke', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/61+Bkc55bFL._SL1500_.jpg'),
       ('Coke Zero', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/71tBUFoCtsL._SL1500_.jpg'),
       ('Sprite', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/71Y5MLLmknL._SL1500_.jpg'),
       ('Iced Tea', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/41PoAsA4a7L.jpg'),
       ('Fanta', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/71wrRjcdrDL._SL1500_.jpg'),
       ('Powerade', '20 FL OZ', 'Drink', 2,'https://m.media-amazon.com/images/I/71s7s4d6KcL._SL1500_.jpg'),
       ('Dasani', '20 FL OZ', 'Drink', 2, 'https://m.media-amazon.com/images/I/31Fo4PL7BVL.jpg'),
       ('Root Beer', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/81pRfPL1WxL._SL1500_.jpg'),
       ('Dr. Pepper', '2 Liter', 'Drink', 3, 'https://m.media-amazon.com/images/I/719IvqhnvTL._SL1500_.jpg');

CREATE TABLE pizza_ingredient (
        pizza_ingredient_id serial,
        pizza_id int CHECK((custom_pizza_id IS NULL AND pizza_id IS NOT NULL) OR (pizza_id IS NULL AND custom_pizza_id IS NOT NULL)),
        custom_pizza_id int CHECK((custom_pizza_id IS NULL AND pizza_id IS NOT NULL) OR (pizza_id IS NULL AND custom_pizza_id IS NOT NULL)),
        ingredient_id int NOT NULL,
        quantity int NOT NULL CHECK (quantity >= 1),
        
        CONSTRAINT PK_pizza_ingredient PRIMARY KEY (pizza_ingredient_id),
        CONSTRAINT FK_pizza_ingredient_pizza_id FOREIGN KEY (pizza_id) REFERENCES menu(item_id),
        CONSTRAINT FK_pizza_ingredient_name FOREIGN KEY (ingredient_id) REFERENCES ingredient(ingredient_id),
        CONSTRAINT FK_pizza_ingredient_custom_pizza_id FOREIGN KEY (custom_pizza_id) REFERENCES custom_pizza(pizza_id)
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
       (12, 34, 1);

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

INSERT INTO orders (first_name, last_name, address_line_1, address_state, address_city, address_zip_code, email, phone_number, order_total, credit_card_number, credit_card_exp_month, credit_card_exp_year, credit_card_ccv)
VALUES ('Han', 'Solo', 'Corellia Street', 'CO','Corellia Central', 12345, 'SanHolo@hmail.com', '9231234323', 45.54, 1234567890123456, 12, 25, 4356),
       ('Leia', 'Skywalker', 'Alderaan Drive', 'AL', 'Alderaan Square', 54321, 'I<3SanHolo@gmail.com', '8324256178', 98.21, 1234567890123456, 12, 25, 4356),
       ('Chewnacca', 'Wookie', 'Kashyyyk Court', 'KA', 'Kashyyyk Central', 31234, 'Chewie@gmail.com', '1111111111', 1.23, 1234567890123456, 12, 25, 4356);
       
INSERT INTO orders (first_name, last_name, address_line_1, address_state, address_city, address_zip_code, email, phone_number, order_total, completed, credit_card_number, credit_card_exp_month, credit_card_exp_year, credit_card_ccv)
VALUES ('Anakin','Skywalker', 'Tatooine', 'TA', 'Tatooine Circle', 31253, 'ILoveMySister@gmail.com', '2223334444', 65.43, TRUE, 1234567890123456, 12, 25, 4356),
       ('Yoda', 'Shrek', 'Dagobah', 'DA', 'Dagobah Bog', 41265, 'JediMasterIAm@gmail.com', '2325462364', 54.32, TRUE, 1234567890123456, 12, 25, 4356),
       ('Mace', 'Windu', 'Haruun Kal', 'HK', 'Haruun Kal Circle', 12353, 'HaveYouSeenMyPurpleLightSabre@gmail.com', '1255436433', 878.43, TRUE, 1234567890123456, 12, 25, 4356),
       ('Obi-Wan', 'Kenobi', 'Stewjon', 'SJ', 'Stew And Beans', 55932, 'YouWereMyBrotherAnakin@gmail.com', '5215427653', 97.23, TRUE, 1234567890123456, 12, 25, 4356),
       ('Qui-Gon', 'Jinn', 'Coruscant', 'CO', 'Crossant', 12353, 'BeatBySomeHornedDude@gmail.com', '2342517647', 75.43, TRUE, 1234567890123456, 12, 25, 4356);
       
CREATE TABLE order_items (
        order_id int NOT NULL,
        item_type varchar(12) NOT NULL CHECK (item_type = 'Menu' OR item_type = 'Custom'),
        menu_item_id int CHECK((custom_pizza_id IS NULL AND menu_item_id IS NOT NULL) OR (menu_item_id IS NULL AND custom_pizza_id IS NOT NULL)),
        custom_pizza_id int CHECK((custom_pizza_id IS NULL AND menu_item_id IS NOT NULL) OR (menu_item_id IS NULL AND custom_pizza_id IS NOT NULL)),
        item_size varchar(12),
        quantity int NOT NULL,
        
        CONSTRAINT FK_order_items_order_id FOREIGN KEY (order_id) REFERENCES orders(order_id),
        CONSTRAINT FK_order_items_menu_item_id FOREIGN KEY (menu_item_id) REFERENCES menu(item_id),
        CONSTRAINT FK_order_items_custom_pizza_id FOREIGN KEY (custom_pizza_id) REFERENCES custom_pizza(pizza_id)
);


COMMIT TRANSACTION;
