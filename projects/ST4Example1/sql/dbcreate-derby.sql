--==============================================================
-- ST4Example DB creation script for Apache Derby
--==============================================================
-- first add the apache nature to your project
-- second run Derby DBMS
-- third run this script with the help of ij utility
--==============================================================

-- this command connects ij to database st4db, if it doesn't exist DBMS creates one
CONNECT 'jdbc:derby://localhost:1527/st4db;create=true;user=test;password=test';

-- these commands remove all tables from the database
-- it implies an error if tables not exist in DB, just ignore it
DROP TABLE orders_menu;
DROP TABLE menu;
DROP TABLE orders;
DROP TABLE categories;
DROP TABLE statuses;
DROP TABLE users;
DROP TABLE roles;

----------------------------------------------------------------
-- ROLES
-- users roles
----------------------------------------------------------------
CREATE TABLE roles(

-- id has the INTEGER type (other name is INT), it is the primary key
	id INTEGER NOT NULL PRIMARY KEY,

-- name has the VARCHAR type - a string with a variable length
-- names values should not be repeated (UNIQUE) 	
	name VARCHAR(10) NOT NULL UNIQUE
);

-- this two commands insert data into roles table
----------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Role entity, so the numeration must started 
-- from 0 with the step equaled to 1
----------------------------------------------------------------
INSERT INTO roles VALUES(0, 'admin');
INSERT INTO roles VALUES(1, 'client');

----------------------------------------------------------------
-- USERS
----------------------------------------------------------------
CREATE TABLE users(

-- 'generated always AS identity' means id is autoincrement field 
-- (from 1 up to Integer.MAX_VALUE with the step 1)
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	
-- 'UNIQUE' means logins values should not be repeated in login column of table	
	login VARCHAR(10) NOT NULL UNIQUE,
	
-- not null string columns	
	password VARCHAR(10) NOT NULL,
	first_name VARCHAR(20) NOT NULL,
	last_name VARCHAR(20) NOT NULL,
	
-- this declaration contains the foreign key constraint	
-- role_id in users table is associated with id in roles table
-- role_id of user = id of role
	role_id INTEGER NOT NULL REFERENCES roles(id) 

-- removing a row with some ID from roles table implies removing 
-- all rows from users table for which ROLES_ID=ID
-- default value is ON DELETE RESTRICT, it means you cannot remove
-- row with some ID from the roles table if there are rows in 
-- users table with ROLES_ID=ID
		ON DELETE CASCADE 

-- the same as previous but updating is used insted deleting
		ON UPDATE RESTRICT
);

-- id = 1
INSERT INTO users VALUES(DEFAULT, 'admin', 'admin', 'Ivan', 'Ivanov', 0);
-- id = 2
INSERT INTO users VALUES(DEFAULT, 'client', 'client', 'Petr', 'Petrov', 1);
-- id = 3
INSERT INTO users VALUES(DEFAULT, 'петров', 'петров', '»ван', 'ѕетров', 1);

----------------------------------------------------------------
-- STATUSES
-- statuses for orders
----------------------------------------------------------------
CREATE TABLE statuses(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

----------------------------------------------------------------
-- ATTENTION!!!
-- we use ENUM as the Status entity, so the numeration must started 
-- from 0 with the step equaled to 1
----------------------------------------------------------------
INSERT INTO statuses VALUES(0, 'opened');
INSERT INTO statuses VALUES(1, 'confirmed');
INSERT INTO statuses VALUES(2, 'paid');
INSERT INTO statuses VALUES(3, 'closed');

----------------------------------------------------------------
-- CATEGORIES
-- categories names of menu
----------------------------------------------------------------
CREATE TABLE categories(
	id INTEGER NOT NULL PRIMARY KEY,
	name VARCHAR(10) NOT NULL UNIQUE
);

INSERT INTO categories VALUES(1, 'Hot dishes'); -- гор€чие блюда
INSERT INTO categories VALUES(2, 'Starters'); -- закуски
INSERT INTO categories VALUES(3, 'Desserts'); -- десерт
INSERT INTO categories VALUES(4, 'Beverages'); -- напитки

----------------------------------------------------------------
-- ORDERS
----------------------------------------------------------------
CREATE TABLE orders(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	bill INTEGER NOT NULL DEFAULT 0,
	user_id INTEGER NOT NULL REFERENCES users(id),
	status_id INTEGER NOT NULL REFERENCES statuses(id) 
);

-- bill = 0; user_id=2; status_id=0
INSERT INTO orders VALUES(DEFAULT, 0, 2, 0);
-- bill = 0; user_id=2; status_id=3
INSERT INTO orders VALUES(DEFAULT, 0, 2, 3);

----------------------------------------------------------------
-- MENU
----------------------------------------------------------------
CREATE TABLE menu(
	id INTEGER NOT NULL generated always AS identity PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	price INTEGER NOT NULL,
	category_id INTEGER NOT NULL REFERENCES categories(id) 
);

-- гор€чие блюда
INSERT INTO menu VALUES(DEFAULT, 'Borsch', 210, 1); -- 1 (order id)
INSERT INTO menu VALUES(DEFAULT, 'Kharcho', 210, 1); -- 2
INSERT INTO menu VALUES(DEFAULT, 'Solyanka', 250, 1); -- 3
-- напитки
INSERT INTO menu VALUES(DEFAULT, 'Juice', 70, 4); -- 4
INSERT INTO menu VALUES(DEFAULT, 'Tea', 50, 4); -- 5
INSERT INTO menu VALUES(DEFAULT, 'Coffee', 100, 4); -- 6
-- закуски
INSERT INTO menu VALUES(DEFAULT, 'Salmon salad', 250, 2); -- 7
INSERT INTO menu VALUES(DEFAULT, 'Fish plate', 200, 2); -- 8
-- десерт        
INSERT INTO menu VALUES(DEFAULT, 'Fruit plate', 160, 3); -- 9
INSERT INTO menu VALUES(DEFAULT, 'Strawberries and cream', 260, 3); --10
                 
----------------------------------------------------------------
-- ORDERS_MENU
-- relation between order and menu
-- each row of this table represents one menu item
----------------------------------------------------------------
CREATE TABLE orders_menu(
	order_id INTEGER NOT NULL REFERENCES orders(id),
	menu_id INTEGER NOT NULL REFERENCES menu(id)
);

INSERT INTO orders_menu VALUES(1, 1);
INSERT INTO orders_menu VALUES(1, 7);
INSERT INTO orders_menu VALUES(1, 5);

INSERT INTO orders_menu VALUES(2, 1);
INSERT INTO orders_menu VALUES(2, 7);
	
----------------------------------------------------------------
-- test database:
----------------------------------------------------------------
SELECT * FROM orders_menu;
SELECT * FROM menu;
SELECT * FROM orders;
SELECT * FROM categories;
SELECT * FROM statuses;
SELECT * FROM users;
SELECT * FROM roles;

DISCONNECT;