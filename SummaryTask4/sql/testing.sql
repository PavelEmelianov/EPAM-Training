CONNECT 'jdbc:derby://localhost:1527/Testing;create=true;user=pavel;password=emelianov';

drop table users_tests;
drop table users;
drop table roles;
drop table answers;
drop table questions;
drop table tests;
drop table themes;

CREATE FUNCTION hash(password_as_string VARCHAR(20))
RETURNS VARCHAR(128)
PARAMETER STYLE JAVA 
NO SQL LANGUAGE JAVA 
EXTERNAL NAME 'ua.nure.emelianov.security.Password.hash';

create table roles(
id INTEGER NOT NULL PRIMARY KEY,
name VARCHAR(10) NOT NULL UNIQUE
);

INSERT into roles values (0,'admin');
INSERT into roles values (1,'student');

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

INSERT into USERS values(default,'admin','0DD3E512642C97CA3F747F9A76E374FBDA73F9292823C0313BE9D78ADD7CDD8F72235AF0C553DD26797E78E1854EDEE0AE002F8ABA074B066DFCE1AF114E32F8','Pavel','Emelianov','emelianov-p@mail.ru',default,0);
INSERT into USERS values(default,'PPetrov','3C9909AFEC25354D551DAE21590BB26E38D53F2173B8D3DC3EEE4C047E7AB1C1EB8B85103E3BE7BA613B31BB5C9C36214DC9F14A42FD7A2FDB84856BCA5C44C2','Petr','Petrov','petrov@mail.ru',default,1);
INSERT into USERS values(default,'IIvanov','3043AA4A83B0934982956A90828140CB834869135B5F294938865DE12E036DE440A330E1E8529BEC15DDD59F18D1161A97BFEC110D2622680F2C714A737D7061','Ivan','Ivanov','ivanov@mail.ru',true,1);

create table themes (
id integer not null generated always as identity primary key,
name varchar(100) not null unique, 
status boolean default true
);

INSERT into THEMES values(default,'Mathematics',default);
INSERT into THEMES values(default,'Physics',default);
INSERT into THEMES values(default,'English',default);

create table tests(
id integer not null generated always as identity primary key,
name varchar(100) not null,
difficulty varchar(20) not null,
questions_count integer not null default 0,
time_for_test integer not null,
themes_id integer not null references themes(id) ON DELETE CASCADE
);

INSERT into tests values(default,'Operations','advanced', default, 500,1);
INSERT into tests values(default,'Numbers','elementary', default, 300,1);
INSERT into tests values(default,'Integrals','proficient', default, 400,1);

INSERT into tests values(default,'Optics','advanced', default, 10,2);
INSERT into tests values(default,'Mechanics','elementary', default, 10,2);
INSERT into tests values(default,'Nuclear physics','proficient', default, 10,2);

INSERT into tests values(default,'Pronouns','advanced', default, 10,3);
INSERT into tests values(default,'Articles','elementary', default, 10,3);
INSERT into tests values(default,'Nouns','proficient', default, 10,3);

create table questions(
id integer not null generated always as identity primary key,
name varchar (100) not null,
tests_id integer not null references tests(id)  ON DELETE CASCADE
);

INSERT into questions values(default,'Question 1MathElementary',1);
INSERT into questions values(default,'Question 2MathElementary',1);
INSERT into questions values(default,'Question 3MathElementary',1);
INSERT into questions values(default,'Question 4MathElementary',1);

INSERT into questions values(default,'Question 1MathAdvanced',2);
INSERT into questions values(default,'Question 2MathAdvanced',2);
INSERT into questions values(default,'Question 3MathAdvanced',2);

INSERT into questions values(default,'Question 1MathMaster',3);
INSERT into questions values(default,'Question 2MathMaster',3);
INSERT into questions values(default,'Question 3MathMaster',3);

INSERT into questions values(default,'Question 1PhysicsElementary',4);
INSERT into questions values(default,'Question 2PhysicsElementary',4);
INSERT into questions values(default,'Question 3PhysicsElementary',4);

INSERT into questions values(default,'Question 1PhysicsAdvanced',5);
INSERT into questions values(default,'Question 2PhysicsAdvanced',5);
INSERT into questions values(default,'Question 3PhysicsAdvanced',5);

INSERT into questions values(default,'Question 1PhysicsMaster',6);
INSERT into questions values(default,'Question 2PhysicsMaster',6);
INSERT into questions values(default,'Question 3PhysicsMaster',6);

INSERT into questions values(default,'Question 1EnglishElementary',7);
INSERT into questions values(default,'Question 2EnglishElementary',7);
INSERT into questions values(default,'Question 3EnglishElementary',7);

INSERT into questions values(default,'Question 1EnglishAdvanced',8);
INSERT into questions values(default,'Question 2EnglishAdvanced',8);
INSERT into questions values(default,'Question 3EnglishAdvanced',8);

INSERT into questions values(default,'Question 1EnglishMaster',9);
INSERT into questions values(default,'Question 2EnglishMaster',9);
INSERT into questions values(default,'Question 3EnglishMaster',9);



create table answers(
id integer not null generated always as identity primary key,
name varchar (100) not null,
correct boolean default false,
questions_id integer not null references questions(id)  ON DELETE CASCADE
);

INSERT into answers values(default,'Answer1Question1MathBasic',false,1);
INSERT into answers values(default,'Answer2Question1MathBasic',true,1);
INSERT into answers values(default,'Answer3Question1MathBasic',false,1);
INSERT into answers values(default,'Answer4Question1MathBasic',true,1);
INSERT into answers values(default,'Answer5Question1MathBasic',false,1);

INSERT into answers values(default,'Answer1Question2MathBasic',false,2);
INSERT into answers values(default,'Answer2Question2MathBasic',true,2);
INSERT into answers values(default,'Answer3Question2MathBasic',false,2);
INSERT into answers values(default,'Answer4Question2MathBasic',true,2);
INSERT into answers values(default,'Answer5Question2MathBasic',false,2);

INSERT into answers values(default,'Answer1Question3MathBasic',false,3);
INSERT into answers values(default,'Answer2Question3MathBasic',true,3);
INSERT into answers values(default,'Answer3Question3MathBasic',true,3);
INSERT into answers values(default,'Answer4Question3MathBasic',true,3);
INSERT into answers values(default,'Answer5Question3MathBasic',false,3);

INSERT into answers values(default,'Answer1Question4MathBasic',false,4);
INSERT into answers values(default,'Answer2Question4MathBasic',true,4);
INSERT into answers values(default,'Answer3Question4MathBasic',false,4);
INSERT into answers values(default,'Answer4Question4MathBasic',true,4);
INSERT into answers values(default,'Answer5Question4MathBasic',false,4);

INSERT into answers values(default,'Answer1Question1MathAdvanced',false,5);
INSERT into answers values(default,'Answer2Question1MathAdvanced',true,5);
INSERT into answers values(default,'Answer3Question1MathAdvanced',false,5);
INSERT into answers values(default,'Answer4Question1MathAdvanced',true,5);
INSERT into answers values(default,'Answer5Question1MathAdvanced',false,5);

INSERT into answers values(default,'Answer1Question2MathAdvanced',false,6);
INSERT into answers values(default,'Answer2Question2MathAdvanced',true,6);
INSERT into answers values(default,'Answer3Question2MathAdvanced',false,6);
INSERT into answers values(default,'Answer4Question2MathAdvanced',true,6);
INSERT into answers values(default,'Answer5Question2MathAdvanced',false,6);

INSERT into answers values(default,'Answer1Question3MathAdvanced',false,7);
INSERT into answers values(default,'Answer2Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer3Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer4Question3MathAdvanced',true,7);
INSERT into answers values(default,'Answer5Question3MathAdvanced',false,7);

INSERT into answers values(default,'Answer1Question1MathMaster',false,8);
INSERT into answers values(default,'Answer2Question1MathMaster',true,8);
INSERT into answers values(default,'Answer3Question1MathMaster',false,8);
INSERT into answers values(default,'Answer4Question1MathMaster',true,8);
INSERT into answers values(default,'Answer5Question1MathMaster',false,8);

INSERT into answers values(default,'Answer1Question2MathMaster',false,9);
INSERT into answers values(default,'Answer2Question2MathMaster',true,9);
INSERT into answers values(default,'Answer3Question2MathMaster',false,9);
INSERT into answers values(default,'Answer4Question2MathMaster',true,9);
INSERT into answers values(default,'Answer5Question2MathMaster',false,9);

INSERT into answers values(default,'Answer1Question3MathMaster',false,10);
INSERT into answers values(default,'Answer2Question3MathMaster',true,10);
INSERT into answers values(default,'Answer3Question3MathMaster',true,10);
INSERT into answers values(default,'Answer4Question3MathMaster',true,10);
INSERT into answers values(default,'Answer5Question3MathMaster',false,10);

create table users_tests(

id integer not null generated always as identity primary key,
users_id integer not null references users(id)  ON DELETE CASCADE,
tests_id integer not null references tests(id)  ON DELETE CASCADE,
dates varchar (50) not null unique,
result integer not null

);



SELECT * from users;




