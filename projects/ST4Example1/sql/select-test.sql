CONNECT 'jdbc:derby://localhost:1527/st4db;user=test;password=test';

SELECT * FROM users;
SELECT * FROM orders;

SELECT o.id, u.first_name, u.last_name, o.bill, s.name
	FROM users u, orders o, statuses s
	WHERE o.user_id=u.id AND o.status_id=s.id;

DISCONNECT;