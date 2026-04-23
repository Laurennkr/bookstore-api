# Bookstore API

API REST para gestión de una librería en línea. Construida con Spring Boot, Spring Security, JWT y H2.

## Tecnologías

- Java 25
- Spring Boot 3.5
- Spring Security + JWT
- Spring Data JPA
- H2 (base de datos en memoria)
- Lombok
- Springdoc OpenAPI (Swagger)

## Configuración y ejecución local

### Requisitos
- Java 17+
- Maven 3.8+

### Pasos

1. Clonar el repositorio
```bash
git clone https://github.com/Laurennkr/bookstore-api.git
cd bookstore-api
```

2. Ejecutar el proyecto
```bash
./mvnw spring-boot:run
```

3. La API estará disponible en:
   http://localhost:8080/api/v1
## Documentación Swagger
http://localhost:8080/api/v1/swagger-ui/index.html
## Consola H2
http://localhost:8080/api/v1/h2-console
JDBC URL: jdbc:h2:mem:bookstoredb
Usuario: sa
Password: (vacío)

## Endpoints principales

### Auth (público)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /auth/register | Registrar usuario |
| POST | /auth/login | Iniciar sesión |

### Books (GET público, escritura solo ADMIN)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| GET | /books | Listar libros (paginado) |
| GET | /books/{id} | Obtener libro |
| POST | /books | Crear libro |
| PUT | /books/{id} | Actualizar libro |
| DELETE | /books/{id} | Eliminar libro |

### Orders (USER autenticado)
| Método | Endpoint | Descripción |
|--------|----------|-------------|
| POST | /orders | Crear pedido |
| GET | /orders/my | Mis pedidos |
| GET | /orders | Todos los pedidos (ADMIN) |
| PATCH | /orders/{id}/cancel | Cancelar pedido |

## Roles

| Rol | Permisos |
|-----|----------|
| ADMIN | CRUD completo de libros, autores y categorías. Ver todos los pedidos |
| USER | Consulta pública. Crear y ver sus propios pedidos |

## Flujo Git

- `main` → producción
- `develop` → integración
- `feature/*` → desarrollo de funcionalidades