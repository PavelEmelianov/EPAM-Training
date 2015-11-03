CONNECT 'jdbc:derby://localhost/p9db;create=true;user=test;password=test';

DROP TABLE users;
DROP  TABLE roles;
					
CREATE TABLE roles (
	id INT PRIMARY KEY,
	name VARCHAR(15) UNIQUE
);

CREATE TABLE users (
	login VARCHAR(50) PRIMARY KEY,
	password VARCHAR(50),
	full_name VARCHAR(50),
	email VARCHAR(50),
	role_id INT REFERENCES roles(id)
);

INSERT INTO roles VALUES (1, 'admin');
INSERT INTO roles VALUES (2, 'client');

INSERT INTO users VALUES ('admin', 'admin', 'Ivan Ivanov', 'ivanov@gmail.com', 1);
INSERT INTO users VALUES ('client', 'client', 'Petr Petrov', 'petrov@gmail.com', 2);
INSERT INTO users VALUES ('client2', 'client2', 'George Bush', 'bush@gmail.com', 2);
INSERT INTO users VALUES ('Клиент', 'Клиент', 'Bon Scott', 'bon@gmail.com', 2);

-- check data

SELECT * FROM roles;
SELECT * FROM users;

DISCONNECT;