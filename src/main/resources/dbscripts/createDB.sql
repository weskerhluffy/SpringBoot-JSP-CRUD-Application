create table USUARIO
(
id IDENTITY PRIMARY KEY,
nombre varchar(50),
contra varchar(50),
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