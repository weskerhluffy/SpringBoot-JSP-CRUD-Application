drop table if exists usuario;
drop table if exists rol;
drop table if exists usuario_rol;
drop table if exists PERSISTENT_LOGINS;
create table usuario
(
id int NOT NULL AUTO_INCREMENT,
nombre varchar(50),
contra varchar(500),
activo boolean,
primary key(id)
);
create table rol
(
id int NOT NULL AUTO_INCREMENT,
nombre varchar(50),
primary key(id)
);

create table usuario_rol
(
	usuario_id int,
	rol_id int
);

create table PERSISTENT_LOGINS
(
series varchar(500),
username varchar(50),
token varchar(500),
last_used date,
primary key(series)
);