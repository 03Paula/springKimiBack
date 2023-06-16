# KimiBack
Backend para el proyecto de final de grado, realizado con SpringBoot.

## Endpoints
### Usuario

| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/usuarios`              | Mostrar todas los usuarios.                                  |  GET   |
| `/kimi/usuario/{id}`          | Mostrar un usuario por su id                                 |  GET   |
| `/kimi/usuario/{id}`          | Permite modificar un usuario por su id                       |  PUT   |
| `/kimi/usuario/{id}`          | Elimina un usuario por su id.                                |  DELETE   |
| `/kimi/usuario`               | Crea un usuario nuevo.                                       |  POST   |
| `/kimi/usuarios/email/{email}`| Mostrar un usuario por su email.                                 |  GET   |

### Tarjetas
| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/tarjetas`              | Mostrar todas las tarjetas.                                  |  GET   |
| `/kimi/tarjeta/{id}`          | Mostrar una tarjeta por su id.                                 |  GET   |
| `/kimi/tarjeta/{id}`          | Permite modificar una tarjeta por su id                       |  PUT   |
| `/kimi/tarjeta/{id}`          | Elimina una tarjeta por su id.                                |  DELETE   |
| `/kimi/tarjeta`               | Crea una tarjeta nueva.                                      |  POST   |
| `/kimi/tarjetas/usuario/{id}` | Mostrar las tarjetas que tengan ese id de usuario            |  GET   |

### Productos
| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/productos`              | Mostrar todos los productos.                                  |  GET   |
| `/kimi/producto/:{id}`          | Mostrar un producto por su id                                 |  GET   |
| `/kimi/producto/{id}`          | Permite modificar un producto por su id                       |  PUT   |
| `/kimi/producto/{id}`          | Elimina un producto por su id.                                |  DELETE   |
| `/kimi/producto`               | Crea un producto nuevo.                                      |  POST   |
| `/kimi/producto/autor/{autor}` | Mostrar los productos que tengan ese autor.            |  GET   |
| `/kimi/producto/categoria/{categoria}` | Mostrar los productos que tengan esa categoria.            |  GET   |
| `/kimi/producto/genero/{genero}` | Mostrar los productos que tengan ese genero.            |  GET   |
| `/kimi/producto/nombre/{nombre}` | Mostrar los productos que contengan ese nombre .            |  GET   |
| `/kimi/productos/page` | Mostrar los productos de forma paginada .            |  GET   |

### Pedidos
| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/pedidos`              | Mostrar todos los pedidos.                                  |  GET   |
| `/kimi/pedido/{id}`          | Mostrar un pedido por su id.                                 |  GET   |
| `/kimi/pedido/{id}`          | Permite modificar un pedido por su id                       |  PUT   |
| `/kimi/pedido/{id}`          | Elimina un pedido por su id.                                |  DELETE   |
| `/kimi/pedido`               | Crea un pedido nuevo.                                      |  POST   |
| `/kimi/pedidos/usuario/{id}` | Mostrar los pedidos que tengan ese id de usuario            |  GET   |

### Direcciones
| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/direcciones`              | Mostrar todas las direcciones.                                  |  GET   |
| `/kimi/direccion/{id}`          | Mostrar una dirección por su id.                                 |  GET   |
| `/kimi/direccion/{id}`          | Permite modificar una dirección por su id                       |  PUT   |
| `/kimi/direccion/{id}`          | Elimina una dirección por su id.                                |  DELETE   |
| `/kimi/direccion`               | Crea una dirección nueva.                                      |  POST   |
| `/kimi/direcciones/usuario/{id}` | Mostrar las direcciones que tengan ese id de usuario            |  GET   |

### Carritos
| Endpoint                      | Resultado                                                    | Método |
|-------------------------------|--------------------------------------------------------------|:------:|
| `/kimi/carritos`              | Mostrar todos los carritos.                                  |  GET   |
| `/kimi/carrito/{id}`          | Mostrar un carrito por su id.                                 |  GET   |
| `/kimi/carrito/{id}`          | Permite modificar un carrito por su id                       |  PUT   |
| `/kimi/carrito/{id}`          | Elimina un carrito por su id.                                |  DELETE   |
| `/kimi/carrito`               | Crea un carrito nuevo.                                      |  POST   |
| `/kimi/carrito/usuario/{id}` | Mostrar los carritos que tengan ese id de usuario            |  GET   |

Para mirar Swagger -> [Swagger](http://localhost:8080/swagger-ui.html)
