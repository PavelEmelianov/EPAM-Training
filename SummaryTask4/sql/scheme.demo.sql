
create table roles(
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(10) NOT NULL UNIQUE
);

create table users(
id INTEGER NOT NULL generated always as identity primary key,
login varchar(20) not null unique,
password varchar(128) not null,
f_name varchar (20) not null,
l_name varchar (20) not null,
e_mail varchar(30) not null,
blocked boolean default false,
role_id integer not null references roles(id)  ON DELETE CASCADE ON UPDATE RESTRICT
);

create table themes (
id integer not null generated always as identity primary key,
name varchar(100) not null unique, 
status boolean default true
);

create table tests(
id integer not null generated always as identity primary key,
name varchar(100) not null,
difficulty varchar(20) not null,
questions_count integer not null default 0,
time_for_test integer not null,
themes_id integer not null references themes(id) ON DELETE CASCADE
);

create table questions(
id integer not null generated always as identity primary key,
name varchar (2000) not null,
tests_id integer not null references tests(id)  ON DELETE CASCADE
);

create table answers(
id integer not null generated always as identity primary key,
name varchar (150) not null,
correct boolean default false,
questions_id integer not null references questions(id)  ON DELETE CASCADE
);

create table users_tests(
id integer not null generated always as identity primary key,
users_id integer not null references users(id)  ON DELETE CASCADE,
tests_id integer not null references tests(id)  ON DELETE CASCADE,
dates varchar (50) not null unique,
result integer not null,
average integer not null
);
