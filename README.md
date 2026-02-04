SuperMercado API REST
Descripción del Proyecto

Este proyecto consiste en el desarrollo de una API REST con Spring Boot para la gestión de un Supermercado, permitiendo administrar productos, categorías, clientes y ventas, incluyendo el control de stock y el cálculo automático del total de cada venta.

El objetivo principal es demostrar el dominio del acceso a datos con JPA, el uso correcto de relaciones entre entidades, la implementación de reglas de negocio, paginación y una arquitectura en capas profesional.

Proyecto desarrollado como trabajo final del módulo Acceso a Datos (2º DAM).

Objetivos del Proyecto

Diseñar un modelo de datos relacional coherente usando JPA (Hibernate).

Implementar reglas de negocio reales en la capa de servicio.

Gestionar correctamente relaciones 1:N y N:M.

Aplicar paginación y consultas avanzadas con Pageable.

Estructurar la aplicación siguiendo una arquitectura en capas.

Garantizar la persistencia correcta de los datos en MySQL.

Modelo de Datos
Entidades del Sistema
📦 Producto (Entidad Principal)

id_producto

nombre

descripción

precio

stock

marca

categoría

🧾 Venta (Entidad Transaccional)

id_venta

fecha

total

método_pago

cliente

lista de detalles de venta

👤 Cliente

id_cliente

nombre

apellido

dni / cédula

teléfono

correo

dirección

fecha_registro

🗂️ Categoría

id_categoria

nombre

descripción

📄 DetalleVenta

id_detalle

cantidad

precio_unitario

subtotal

venta

producto

Relaciones y Cardinalidad

Categoría → Producto:
Relación Uno a Muchos (1:N)
Una categoría puede tener muchos productos, pero un producto pertenece a una sola categoría.

Cliente → Venta:
Relación Uno a Muchos (1:N)
Un cliente puede realizar muchas ventas, pero cada venta pertenece a un único cliente.

Producto ↔ Venta:
Relación Muchos a Muchos (N:M)
Implementada mediante la entidad DetalleVenta.

Venta → DetalleVenta:
Relación Uno a Muchos (1:N)
Una venta puede tener varios detalles de venta.

Producto → DetalleVenta:
Relación Uno a Muchos (1:N)
Un producto puede aparecer en muchos detalles de venta.

Para evitar recursividad infinita en las respuestas JSON se utilizan DTOs y/o anotaciones como @JsonIgnoreProperties.

Reglas de Negocio Implementadas

Una categoría puede existir aunque no tenga productos.

Un producto no puede existir sin categoría.

Un producto debe pertenecer obligatoriamente a una categoría.

No se puede vender un producto si su stock es 0.

Al realizar una venta, el stock del producto se actualiza automáticamente.

Un cliente puede existir aunque no haya realizado ventas.

Una venta debe tener obligatoriamente:

Un cliente asociado

Al menos un producto

Una fecha

Al menos un detalle de venta

No existe venta sin productos.

Un detalle de venta:

Debe pertenecer a una venta

Debe referenciar un producto existente

Debe tener cantidad mayor que 0

Si alguna regla se incumple, la API devuelve un error controlado en formato JSON.

Flujo de una Venta

Se selecciona un cliente existente (con o sin ventas previas).

Se crea una venta con la fecha actual.

Se agregan uno o más detalles de venta:

Se selecciona el producto.

Se valida el stock disponible.

Se descuenta el stock.

Se calcula automáticamente el total de la venta.

Se guarda la venta en la base de datos.

Endpoints Principales
📦 Productos

POST /productos

GET /productos

GET /productos/{id}

PUT /productos/{id}

DELETE /productos/{id}

👤 Clientes

POST /clientes

GET /clientes

GET /clientes/{id}

PUT /clientes/{id}

DELETE /clientes/{id}

🗂️ Categorías

POST /categorias

GET /categorias

PUT /categorias/{id}

DELETE /categorias/{id}

🧾 Ventas

POST /ventas

GET /ventas

GET /ventas/{id}

Incluye paginación usando Pageable y Page<T> en los listados.

Paginación y Consultas Avanzadas

Uso de Pageable para listar productos y ventas.

Respuesta paginada con:

content

totalPages

totalElements

number

Consultas filtradas por distintos criterios (ej: cliente, fecha, producto).

Arquitectura del Proyecto

Controller: Maneja las peticiones HTTP.

Service: Contiene la lógica de negocio y validaciones.

Repository: Acceso a datos mediante JPA.

DTOs: Desacopla la API del modelo de base de datos.

Uso exclusivo de LocalDate y LocalDateTime.

Tecnologías Utilizadas

Java 17+

Spring Boot

Spring Data JPA (Hibernate)

H2

Maven

Postman

Git & GitHub
