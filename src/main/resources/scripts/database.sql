SET NAMES utf8;
SET time_zone = '+02:00';
SET sql_mode = '';
DROP DATABASE IF EXISTS `kimi-shop`;
CREATE DATABASE `kimi-shop` CHARACTER
SET utf8mb4 COLLATE utf8mb4_unicode_ci;

USE `kimi-shop`;

SET @OLD_TIME_ZONE=@@TIME_ZONE ;
SET TIME_ZONE='+00:00' ;
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 ;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' ;
SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 ;

create table if not exists `kimi-shop`.clientes
(
    id          bigint auto_increment
        primary key,
    apellidos   varchar(50) not null,
    contrasenia varchar(255) not null,
    email       varchar(100) not null,
    nombre      varchar(50) not null,
    rol         varchar(255) not null,
    telefono    varchar(20) not null,
    constraint UK_email_clientes
        unique (email)
);

create table if not exists `kimi-shop`.direcciones
(
    id            bigint auto_increment
        primary key,
    ciudad        varchar(30)  not null,
    codigo_postal int          not null,
    nombre_calle  varchar(100) not null,
    pais          varchar(30)  not null,
    piso          varchar(20)  null,
    provincia     varchar(30)  not null,
    id_usuario    bigint      not null,
    constraint FK_direcciones_cliente
        foreign key (id_usuario) references `kimi-shop`.clientes (id) on delete cascade on update cascade
);

create table if not exists `kimi-shop`.pedidos
(
    id            bigint auto_increment
        primary key,
    estado_pedido varchar(30) not null,
    fecha_pedido  datetime   not null,
    id_cliente    bigint     not null,
    precio_total double not null,
    constraint FK_pedido_cliente
        foreign key (id_cliente) references `kimi-shop`.clientes (id)
);


create table if not exists `kimi-shop`.pedidos_productos
(
    pedido_id   bigint not null,
    producto_id bigint not null,
    constraint FK_pedido_id
        foreign key (pedido_id) references `kimi-shop`.pedidos (id),
    constraint FK_producto_id
        foreign key (producto_id) references `kimi-shop`.productos (id)
);

create table if not exists `kimi-shop`.productos
(
    id                bigint auto_increment
        primary key,
    autor             varchar(100) not null,
    cantidad          int          not null,
    categoria         varchar(20)  not null,
    descripcion       varchar(500) not null,
    fecha_salida      varchar(255) not null,
    formato_coleccion varchar(100) not null,
    genero_serie      varchar(100) not null,
    imagen            varchar(255) not null,
    nombre            varchar(100) not null,
    paginas           int          null,
    precio            float        not null,
    tamanio           varchar(50)  not null
);

create table if not exists `kimi-shop`.carrito(
    id            bigint auto_increment
    primary key,
    id_cliente    bigint     not null,
    precio_total double not null,
    cantidad int not null default 1,
    constraint FK_carrito_cliente
        foreign key (id_cliente) references `kimi-shop`.clientes (id)
);

create table if not exists `kimi-shop`.carrito_productos
(
   carrito_id   bigint not null,
    producto_id bigint not null,
    constraint FK_carrito_id
        foreign key (carrito_id) references `kimi-shop`.carrito (id),
    constraint FK_producto_carrito_id
        foreign key (producto_id) references `kimi-shop`.productos (id)
);

create table if not exists `kimi-shop`.tarjetas
(
    id          bigint auto_increment
        primary key,
    cvv         int          not null,
    n_tarjeta   varchar(30)  not null,
    titular     varchar(100) not null,
    vencimiento varchar(255) not null,
    id_usuario  bigint      not null,
    constraint FK_tarjeta_cliente
        foreign key (id_usuario) references `kimi-shop`.clientes (id) on delete cascade on update cascade
);

insert into `kimi-shop`.clientes (id, apellidos, contrasenia, email, nombre, rol, telefono)
values (1,'Armario Cuadro', 'Pestillo123', 'admin@gmail.com', 'Admin', 'ADMIN', '765543211'),
       (2,'Flor García', 'Pestillo123', '03paulaflor@gmail.com', 'Paula', 'ADMIN', '765543211'),
       (3, 'Jimenez Perez', 'Pestillo123', 'paco@gmail.com', 'Paco', 'USUARIO', '985121233'),
       (4, 'Lorca Benitez', 'Pestillo123', 'lorquita@gmail.com', 'Juan Manuel', 'USUARIO', '665234123'),
       (5, 'Rodriguez Rodriguez', 'Pestillo123', 'lulurodri@gmail.com', 'Lucía', 'USUARIO', '777666444'),
       (6, 'García Román', 'Pestillo123', 'amai@gamil.com', 'Amaya', 'USUARIO', '234112233'),
       (7,'Carmona Marquez' , 'Pestillo123', 'carmar11@gmail.com', 'Juan', 'USUARIO', '656432111'),
       (8, 'Gomez Lugos', 'Pestillo123', 'clugo11@gmail.com', 'Carlos', 'USUARIO', '234432112'),
       (9,'García García', 'Pestillo123', 'Luisgg@gmail.com', 'Luis', 'USUARIO', '634112399');

insert into `kimi-shop`.direcciones (id, ciudad, codigo_postal, nombre_calle, pais, piso, provincia, id_usuario)
values (1,'Madrid', 28001, 'Calle de Alcalá', 'España', '4º', 'Madrid', 3),
       (2,'Barcelona', 08002, 'Passeig de Gràcia', 'España', 'Principal', 'Barcelona', 4),
       (3,'Valencia', 46001, 'Plaza de la Reina', 'España', '1º', 'Valencia', 3),
       (4, 'Sevilla', 41004, 'Calle Sierpes', 'España', '2º', 'Sevilla', 5),
       (5, 'Bilbao', 48001, 'Gran Vía', 'España', '8º', 'Vizcaya', 6),
       (6,'Málaga', 29015, 'Calle Larios', 'España', '3º', 'Málaga', 5),
       (7, 'Zaragoza', 50003, 'Paseo de la Independencia', 'España', '1ª', 'Zaragoza', 7),
       (8, 'Alicante', 03002, 'Avenida de Maisonnave', 'España',null, 'Alicante', 8),
       (9, 'Palma de Mallorca', 07001, 'Passeig des Born Nº120', 'España',null, 'Islas Balerare', 9),
       (10, 'Granada', 18001, 'Calle Recogidas Nª22' , 'España', null, 'Granada', 7),
       (11, 'Valladolid', '47001', 'Calle Santiago', 'España', '2º', 'Valladolid', 5),
       (12, 'A Coruña', '15001', 'Calle Real', 'España', '1º', 'A Coruña', 4);

insert into `kimi-shop`.tarjetas (id, cvv, n_tarjeta, titular, vencimiento, id_usuario)
values (1,123, '1234 5678 9012 3456', 'Paco Jimenez', '06/25', 3),
       (2, 456, '9876 5432 1098 7654', 'Juan Manuel Lorca', '12/24', 4 ),
       (3 ,789, '5678 9012 3456 7890', 'Lucía Rodríguez', '03/27', 5),
       (4 ,234, '4567 8901 2345 6789', 'Amaya García', '09/23', 6),
       (5 ,567, '9012 3456 7890 1234', 'Juan Carmona', '02/26', 7),
       (6 ,890, '3456 7890 1234 5678', 'Carlos Gomez', '07/25', 8),
       (7, 345, '7890 1234 5678 9012', 'Luis García', '11/24', 9),
       (8, 678, '2345 6789 0123 4567', 'Juan Manuel Lorca', '08/23', 4),
       (9, 901, '6789 0123 4567 8901', 'Amaya García', '01/26', 6),
       (10, 123, '0123 4567 8901 2345', 'Juan Carmona', '10/27', 7);