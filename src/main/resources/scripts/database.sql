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
    descripcion       varchar(1000) not null,
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
values (1,'Armario Cuadro', '$2a$10$aaN1q6uIAQaJltjisEiI6eMlyI/Lx6uPgmIPBqiEexo1GTLPne/ga', 'admin@gmail.com', 'Admin', 'ADMIN', '765543211'),
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


insert into `kimi-shop`.productos (id, categoria, nombre, precio, cantidad, imagen, descripcion, tamanio, autor, genero_serie, formato_coleccion, fecha_salida, paginas)
values (1,'Manga','Berserk',16.10,7,'https://springkimiback-production.up.railway.app/berserk01.webp','Edición de lujo de una de las más aclamadas series manga de todos los tiempos. Un viaje épico y salvaje a un reino de fantasía. Guts es un guerrero vestido de negro de los pies a la cabeza que porta una gigantesca espada más larga que su propia estatura y un robusto brazo ortopédico de hierro','15x21 cm','Kentaro Miura','Acción','Tapa blanda con sobrecubierta','28 jun 2017', 456),
       (2,'Manga','Chainsaw Man',8.55,11,'https://springkimiback-production.up.railway.app/chainsawman01.webp','Denji es un chico sin un duro que se deja la piel trabajando como Devil Hunter junto a su perro demoníaco Pochita para resarcir una deuda astronómica, pero entonces... ¡¡una sangrienta traición da un giro radical a su miserable vida!!','11.5x17.5 cm','Tatsuki Fujimoto','Acción','Tapa blanda con sobrecubierta','4 sept 2020',192),
       (3,'Manga', 'Dragon Ball',9.45,15,'https://springkimiback-production.up.railway.app/dragonBall01.webp','Son Goku, es un niño muy especial que ha tenido que valerse por sí mismo en medio de la naturaleza y sin contacto con otras personas. Un día conoció a Bulma, una chica que busca las siete Esferas del Dragón, estos son elementos mágicos que al ser reunidos invocan a un dios dragón llamado Shenlong, el cual concederá un único deseo, cualquiera que este sea. Goku se une a Bulma en la búsqueda de las esferas sin imaginar que hay otros seres muy peligrosos que también las desean','11.1x17.7 cm','Akira Toriyama','Acción','Tapa blanda con sobrecubierta','21 oct 2014',192),
       (4,'Manga','One piece',7.55,4,'https://springkimiback-production.up.railway.app/onePiece01.webp','Hace veintidós años, el legendario pirata, Gold Roger fue ejecutado. Sus últimas palabras fueron que su tesoro conocido como "One Piece" estaba escondido en algún lugar dentro del vasto océano conocido como Grand Line. Esto dio inicio a la Era de los Piratas. Ahora, Monkey D. Luffy, un joven que, inspirado por su ídolo de la infancia emprende su viaje buscando el One Piece y convertirse en el Rey de los Piratas','11x18 cm','Eiichiro Oda','Acción','Tapa blanda con sobrecubierta','13 sept 2006',192),
       (5,'Manga','Haikyu',7.55,16,'https://springkimiback-production.up.railway.app/haikyu01.webp','¡¡Puedo saltar!! A Shôyô Hinata le fascina el vóleibol, así que, en la escuela media, participa en su primer y último partido oficial de la temporada. Sin embargo, sufre una derrota aplastante a manos de la joven promesa Tobio Kageyama, apodado el “Rey de la cancha”. Así pues, ¡Hinata jura vengarse y solicita plaza para entrar en el Club de Vóleibol de la escuela superior Karasuno!','11x17.5 cm','Haruichi Furudate','Deportes','Tapa blanda con sobrecubierta','20 oct 2021',192),
       (6,'Manga','Spy X Family',7.60,13,'https://springkimiback-production.up.railway.app/spyFamily01.webp','El inigualable espía conocido como Twilight es el mejor agente de Westalis que tiene por objetivo encargarse del poderoso Donovan Desmond. El problema es que Desmond es un ermitaño antisocial cuyas únicas apariciones públicas son en los actos escolares de su hijo. Twilight deberá acercarse al objetivo lo suficiente como para desbaratar su agenda secreta. Para ello, sólo deberá simular que es un hombre de familia... con el pequeño detalle de que sólo tiene 7 días para conseguir la familia ideal. Es por eso que bajo la identidad del psiquiatra Loid Forger, Twilight reclutará a Yor Briar, una civil ostaniana que quiere tener bajo perfil y Anya, una huerfanita que sólo busca una familia cariñosa, para hacer las partes de esposa e hija respectivamente. Pero resulta que este par tampoco es nada normal, especialmente si tenemos en cuenta que Yor es una asesina profesional buscada por ambos bandos y Anya es una prófuga de un laboratorio secreto donde consiguió poderes telepáticos','12x17 cm','Tetsuya Endo','Comedia','Tapa blanda con sobrecubierta','11 jun 2020',200),
       (7,'Manga','Banana Fish',16.10,19,'https://springkimiback-production.up.railway.app/bananaFish01.webp','Año 1973, Vietnam. Un soldado pierde los papeles tras musitar las palabras “Banana Fish”... Año 1985, Nueva York. Ash intenta averiguar qué hay detrás de esas palabras, tras las cuales parece cernirse la oscura sombra de Papa Dino, un capo mafioso de los bajos fondos','15x21 cm','Akimi Yoshida','Drama','Tapa blanda con sobrecubierta','24 oct 2019',376),
       (8,'Manga','Shingeki no kyojin',8.55,12,'https://springkimiback-production.up.railway.app/shingeky01.webp','La raza humana, antaño dueña del mundo, se enfrenta a la extinción a manos de los titanes, gigantescos monstruos de inteligencia limitada que cazan y devoran personas por diversión. Los supervivientes se hacinan e intentan sobrevivir en una pequeña ciudad… pero algunos ya están hartos: ¡van a atacar!','10.5x17.5 cm','Hajime Isayama','Acción','Tapa blanda con sobrecubierta','28 sept 2012',184),
       (9,'Manga','The Promised Neverland',7.60,4,'https://springkimiback-production.up.railway.app/promisedNeverland01.webp','Emma, Norman y Ray son tres huérfanos que viven felices en el idílico orfanato Grace Field House, esperando el momento en el que se les asignará una familia adoptiva. Todo cambia cuando descubren accidentalmente la horrorosa realidad de su existencia, así que deciden rebelarse y luchar hasta las últimas consecuencias en una oscura y aterradora aventura. Pero su tiempo se acaba…','11,5x17,5 cm','Kaiu Shirai','Misterio','Tapa blanda con sobrecubierta','12 abr 2018',192),
       (10,'Manga','Fruits basket',14.20,17,'https://springkimiback-production.up.railway.app/fruitBasquet01.webp','Tôru no ha tenido una vida fácil, pero siempre afronta los sinsabores de la vida con una sonrisa. Cuando descubre el secreto de los Sôma, una misteriosa familia presa de una terrible maldición, decide guardarles el secreto y se integra en el día a día de estos peculiares personajes...','13x18 cm','Natsuki Takaya','Romance','Tapa blanda con sobrecubierta','25 ago 2017',392),
       (11,'Manga','Boku no hero Academia',7.55,14,'https://springkimiback-production.up.railway.app/bnha01.webp','Estamos en un mundo donde abundan los superhéroes (y los supervillanos). Los mejores humanos son entrenados en la Academia de Héroes para optimizar sus poderes.Entre la minoría normal, sin poder alguno, aparece Izuku Midoriya, dispuesto a ser una excepción y formarse en la Academia','11x18 cm','Kohei Horikoshi','Acción','Tapa blanda con sobrecubierta','16 oct 2016',192),
       (12,'Manga','Neon Genesis evangelion',14.20,12,'https://springkimiback-production.up.railway.app/evangelion01.webp','En el año 2015 la humanidad intenta recuperarse del “Segundo impacto”, una catástrofe provocada por la caída de un meteorito en la Antártida años atrás. Tras sobrevivir al deshielo de los polos y a una inmediata sucesión de guerras sin fin, los seres humanos deben enfrentarse a un nuevo y mortal peligro. Unos misteriosos seres denominados Ángeles aparecen de improvisto llevando la destrucción por donde pasan. Para frenar esta amenaza, el científico Gendo Ikari ha desarrollado los Evangelion, gigantescos robots que se convierten en la última línea de defensa para la humanidad. Uno de los primeros pilotos escogidos recibe el nombre de “Tercer Elegido”. Shinji Ikari, hijo que Gendo abandonó, se verá obligado a sondear las profundidades de sus propios recursos internos y así encontrar el valor y la fuerza, no sólo para luchar… sino para sobrevivir o arriesgarse a perderlo todo','15x21 cm','Yoshiyuki Sadamoto','Ciencia ficción','Tapa blanda con sobrecubierta','6 may 2022',320),
       (13, 'Manga', 'Buenas noches Punpun', 8.50,7,'https://springkimiback-production.up.railway.app/punPun01.webp','Punpun no es el típico estudiante de primaria. Pese a que siente los mismos impulsos que sus compañeros, empieza a descubrir a las chicas y el sexo, la situación familiar no es la más estable y deberá enfrentarse a ello como buenamente pueda… y en principio, eso es visualizándose como un ave amorfa en un entorno extraño.','13x18,2 cm','Inio Asano','Psicológico','Tapa blanda con sobrecubierta','29 oct 2015',224),
       (14, 'Manga', 'Kaguya-sama: Love is war',8.50,15,'https://springkimiback-production.up.railway.app/kaguya-sama01.webp','La renombrada Academia Shuchiin tiene a dos destacados estudiantes: Miyuki Shirogane y Kaguya Shinomiya. Los dos son estudiantes modélicos: están entre los mejores de Japón, son el orgullo de profesores y alumnos por igual y además, mientras que Miyuki es el presidente del consejo estudiantil, Kaguya es la vicepresidenta. Por si fuera poco, Kaguya pertenece a una adinerada familia y sobresale en gran variedad de disciplinas, aunque sus orígenes la hacen una persona fría y orgullosa. Parecen la pareja perfecta a ojos de todos, aunque no estén juntos. Pero como el roce hace el cariño, se han pillado el uno del otro. Sin embargo su enorme orgullo, hace que no puedan decirse lo que sienten y empieza una infernal batalla entre ellos para conseguir que el otro confiese lo que siente. Las estrategias para lograr la confesión del contrario se suceden e irán escalando y sólo uno puede emerger victorioso en esta pasional lucha romántica. El honor de los dos está en juego!','13x18 cm', 'Aka Akasaka','Romance', 'Tapa blanda con sobrecubierta', '19 nov 2020', 200),
       (15, 'Manga', 'Vinland Saga', 12.95, 13, 'https://springkimiback-production.up.railway.app/vinlandSaga01.webp', 'Desafiando las rígidas leyes vikingas y a pesar de ser un gran guerrero, Thors decide huir de la cruenta vida que llevaba con su familia. Al ser descubierto, será perseguido durante su viaje marítimo por un mercenario de nombre Askeladd, cayendo finalmente en una emboscada junto a su expedición. Ganará la batalla contra sus atacantes, aunque a un alto precio: Thors dará su vida para que el resto de la tripulación, incluido su hijo Thorfinn, vivan. Desde aquel instante Thorfinn jura vengarse. Sin embargo, será apresado por Askeladd y obligado a enrolarse en su barco. Pero aún le quedará una esperanza. Según el código vikingo, Thorfinn tendrá derecho de retar a Askeladd a duelo si cumple una serie de difíciles tareas, como sabotear o matar a generales enemigos, lo que lo les llevará a involucrarse incluso en la guerra por la corona de Inglaterra.','14.8X21 cm', 'Makoto Yukimura','Acción', 'Tapa blanda con sobrecubierta', '21 oct 2014', 220),
       (16, 'Manga', 'Tokyo Ghoul', 8.00, 10, 'https://springkimiback-production.up.railway.app/tokyoGhoul01.webp','Los habitantes de Tokio viven aterrados: en las sombras de la metrópolis se ocultan horribles monstruos que se camuflan entre la multitud, cazando y devorando seres humanos. Un joven se topa con uno de estos ghouls, pero aunque consigue sobrevivir, su existencia ya no será igual...', '11,5x17,5 cm','Sui Ishida','Thriller', 'Tapa blanda con sobrecubierta', '20 mar 2015',224),
       (17, 'Manga', 'Tokyo Revengers', 16.00, 9,'https://springkimiback-production.up.railway.app/tokyoRevengers01.webp','Takemichi, un fracasado que fue miembro de una banda en su juventud, salta 12 años atrás en el tiempo para volver al instituto y salvar a su exnovia, Hina, que en la actualidad ha sido asesinada por la organización criminal Tokyo Manjikai. Con cada viaje temporal, Takemichi influye en los que le rodean y poco a poco va cambiando el pasado… ¿pero eso será suficiente para salvar a Hina y evitar que la Tokyo Manjikai se convierta en una temida banda criminal?','15x21 cm', 'Ken Wakui','Acción', 'Tapa blanda con sobrecubierta','12 nov 2021',384),
       (18, 'Manga', 'Vagabond',7.60,4,'https://springkimiback-production.up.railway.app/vagabond01.webp','Cuenta la historia del legendario espadachín Musashi Miyamoto, la figura histórica más importante del Japón en lo que se refiere al desarrollo de las técnicas de lucha con espada. Desde su juventud como el violento e iracundo joven llamado Takezo, sobreviviendo (aun estando del lado perdedor) a una de las batallas más sangrientas de la historia: Sekigahara; hasta su decisión de pasar a llamarse Musashi y embarcarse en una búsqueda de autosuperación personal que lo llevará a enfrentarse con los más grandes expertos de las artes marciales del país.','13x18 cm', 'Akehiko Inoue', 'Drama', 'Tapa blanda con sobrecubierta', '14 nov 2013',260),
       (19, 'Manga', 'Dr Stone', 7.60, 11, 'https://springkimiback-production.up.railway.app/drStone01.webp','Por causas desconocidas llevan al total de la humanidad a convertirse instantáneamente en piedra. Después de 3.689 años y 158 días, durante los que ha luchado por no perder la consciencia y desaparecer, Taiju nalmente emerge de la piedra y vuelve a la normalidad. Aunque sólo para descubrir que el resto de la humanidad jamás despertó de su sueño pétreo. Luego se entera de que su amigo Senku, un genio absoluto de vo- cación cientí ca y mentalidad mucho más pragmática, también ha sobrevivido, y juntos encuentran una fórmula para volver a la vida a muchas de las estatuas que nose han partido en pedazos con el paso de los siglos. Llegados a este punto se marcan como objetivo reconstruir la civilización desde sus cimientos y tratar de alcanzar el mismo desarrollo tecnológico que había antes de la catástrofe de origen desconocido. Pero tienen que hacerlo de la forma más acelerada posible ya que no quieren morirse en la edad de piedra.','13x18 cm','Riichiro Inagaki, Boichi','Ciencia ficción','Tapa blanda con sobrecubierta','17 may 2018',200),
       (20, 'Manga', 'Kaiju 8', 2.95, 15, 'https://springkimiback-production.up.railway.app/kaiju01.webp', 'Japón tiene la mayor tasa de incidencias por ataques de Kaijus de todo el mundo. Los Kaijus irrumpen continuamente y sin piedad en las vidas cotidianas de la gente. Kafka Hibino, que siempre quiso ser un miembro de las Fuerzas de Defensa, actualmente trabaja como limpiador de cadáveres de Kaijus.Sin embargo, un día, una misteriosa criatura convierte a Kafka en un Kaiju, y las Fuerzas de Defensa de Japón, encargados de derrotar a estos monstruos, le identificaron con el nombre clave de “Kaiju N.8”.','11,5x17,5 cm', 'Naoya Matsumoto','Aventura', 'Tapa blanda con sobrecubierta', '31 ago 2022',208 ),
       (21, 'Figura', 'Figura Shinobo Kocho', 78.40, 2, 'https://springkimiback-production.up.railway.app/figuraShinobu.jpg','Figura del anime Kimetsu no Yaiba, fabricada en PVC, tamaño aprox. 15 cm. Viene con una caja con ventana.', '15 cm','Aniplex', 'Kimetsu no yaiba', 'Conofig', '29 mar 2023',null),
        (22, 'Figura', 'Figura Loid Forger', 45.36, 4,'https://springkimiback-production.up.railway.app/figuraLoid.jpg','Good Smile Company presenta dentro de su línea de productos la figura de LOID FORGER FIG 17 CM SPY X FAMILY POP UP PARADE . Se trata de una figura hecha en PVC. Incluye base para su exposición.','17 cm', ' Good Smile Company', 'Spy x Family','Pop up parade','1 jun 2023', null),
        (23, 'Figura', 'Figura Frieza', 26.32, 7, 'https://springkimiback-production.up.railway.app/frieza.jpg','Banpresto presenta dentro de su línea de productos la figura de FRIEZA FIG 13 CM DRAGON BALL Z HISTORY BOX VOL. 5 . Se trata de una figura hecha en PVC. Incluye base para su exposición.','13 cm', 'Banpresto','Dragon Ball Z', 'History Box', '19 may 2023',null),
        (24, 'Figura', 'Figura Kyoko Kirigiri', 45.36, 2,'https://springkimiback-production.up.railway.app/figuraKyoko.jpg', 'Good Smile Company presenta dentro de su línea de productos la figura de KYOKO KIRIGIRI de 17 CM DANGARONPA POP UP PARADE. Se trata de una figura hecha en PVC. Incluye base para su exposición.',' 17 cm', 'Good Smile Company','Dangaronpa','Pop up parade', '16 may 2023',null),
        (25, 'Figura', 'Figura Ochaco Uraraka', 313.50, 1, 'https://springkimiback-production.up.railway.app/figuraOchaco.jpg', 'Figura a escala 1/7 de "My Hero Academia", fabricada en PVC, mide 28 cm. Viene en una caja con ventana.', '28 cm', 'Spiritale', 'Boku no hero academia', 'Spiritale', '12 may 2023',null),
        (26, 'Figura', 'Figura Makima', 22.40, 4, 'https://springkimiback-production.up.railway.app/figuraMakima.jpg', 'FuRyu presenta dentro de su línea de productos la figura de MAKIMA FIG 10 CM CHAINSAW MAN HIKKAKE FIG . Se trata de una figura hecha en PVC. Incluye base para su exposición.','10 cm','FuRyu','Chainsaw man', 'Hikkake', '10 may 2023', null),
        (27, 'Figura', 'Figura Shiro', 56.00, 8, 'https://springkimiback-production.up.railway.app/figuraShiro.jpg', 'Good Smile presenta dentro de su línea de productos la figura de SHIRO de 10 CM NO GAME NO LIFE NENDOROID. Se trata de una figura articulada hecha en PVC. Incluye complementos y base para su exposición.','10 cm', 'Good Smile', 'No game no life', 'Nendoroid', '10 may 2023',null),
        (28, 'Figura', 'Figura Hatsune Miku', 68.35,5,'https://springkimiback-production.up.railway.app/figuraHatsune.jpg', 'FuRyu presenta dentro de su línea de productos la figura de HATSUNE MIKU LIGHT FIG 22 CM HATSUNE MIKU TENITOL . Se trata de una figura hecha en PVC. Incluye base para su exposición.','22 cm', 'FuRyu', 'Hatsune Miku', 'Tenitol', '6 may 2023',null),
        (29,'Figura', 'Figura Portgas D.Ace', 27.95,2,'https://springkimiback-production.up.railway.app/figuradAce.jpg','Banpresto presenta dentro de su línea de productos la figura de PORTGAS D. ACE ONE PIECE 9 cm SENKOZEKKEI. Se trata de una figura hecha en PVC. Incluye base para su exposición.','9 cm', 'Banpresto', 'One Piece', 'Senkozekkei', '6 may 2023',null),
        (30, 'Figura', 'Figura Natsu Dragnell', 65.55, 4,'https://springkimiback-production.up.railway.app/figuraNatsu.jpg', 'Figura de la popular serie de anime "Fairy Tail Final Season" viene un Nendoroid del Dragon Slayer que siempre cuida a sus amigos, Natsu Dragneel! Viene con tres placas faciales que incluyen una expresión estándar intrépida, una expresión de grito enérgico y una expresión alegre y encantadora.','10 cm','Max Factory','Fairy Tail','Nendoroid','14 abr 2023', null);






