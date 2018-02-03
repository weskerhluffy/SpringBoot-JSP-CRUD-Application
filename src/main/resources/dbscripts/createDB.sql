create table USUARIO
(
id IDENTITY PRIMARY KEY,
nombre varchar(50),
contra varchar(500),
activo boolean
);
create table rol
(
id IDENTITY PRIMARY KEY,
nombre varchar(50)
);

create table usuario_rol
(
	usuario_id int,
	rol_id int
);

create table PERSISTENT_LOGINS
(
series varchar(500) PRimary key,
username varchar(50),
token varchar(500),
last_used date
);