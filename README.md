# 🚗 ConcesionariaApp - Backend

Sistema de gestión para una concesionaria de vehículos, desarrollado en **Java 17 con Spring Boot**, que administra vehículos, clientes, empleados, ventas, reparaciones, repuestos y más.

## 🧱 Estructura del Proyecto

├── src/
│ ├── main/
│ │ ├── java/com/concesionarioapp/
│ │ │ ├── controller/ # Controladores REST
│ │ │ ├── dto/ # DTOs para entrada/salida
│ │ │ ├── entity/ # Entidades JPA
│ │ │ ├── mapper/ # MapStruct o manual mapping
│ │ │ ├── repository/ # Repositorios JPA
│ │ │ ├── security/ # Filtros JWT, config, login
│ │ │ └── service/ # Lógica de negocio
│ │ └── resources/
│ │ ├── application.properties # Configuración (DB, puerto, JWT, etc)
│ │ └── static/ # Archivos estáticos si se expone frontend desde backend
│
├── pom.xml # Dependencias y configuración Maven
├── .gitignore
└── README.md # Este archivo

---

## 🔐 Seguridad

- Autenticación con **JWT (JSON Web Token)**
- Filtro personalizado: `JwtAuthenticationFilter`
- Accesos restringidos por rol: `ADMIN`, `USUARIO`
- Uso de `Spring Security` para control total de endpoints

---

## 🧠 Funcionalidades implementadas

✅ Registro y autenticación de usuarios  
✅ Gestión de clientes y empleados  
✅ CRUD completo de vehículos, modelos, ventas y repuestos  
✅ Reparaciones con asignación de mecánicos y cálculo de costos  
✅ Importación de pedidos y gestión de stock  
✅ Reportes por fechas, mecánicos, repuestos usados, ingresos totales  
✅ Relaciones complejas con tablas intermedias (`reparacion_mecanico`, `modelo_equipo_extra`, etc.)

---

## 🛠 Tecnologías

- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Maven**
- **MySQL**
- **MapStruct** (si se usa mapping automático)
- **Swagger (opcional)**

---

## ⚙️ Configuración

1. Clonar el repositorio:

git clone https://github.com/Henry-Lopez/ConcesionariaApp.git
Crear la base de datos en MySQL (con el mismo nombre que uses en application.properties)

Configurar src/main/resources/application.properties:

properties

spring.datasource.url=jdbc:mysql://localhost:3306/concesionaria
spring.datasource.username=root
spring.datasource.password=tu_clave

jwt.secret=secretoPersonalizado123
jwt.expiration=3600000
Ejecutar desde IntelliJ o con Maven:

mvn spring-boot:run
🌐 Endpoints base
/api/auth/login

/api/vehiculos

/api/clientes

/api/reparaciones

/api/reportes

etc.

---
## Implementacion de la base de datos

- Archivo de la base de datos creada en MySql en el txt db(2).
- Copiar y Ejecutar en cualquier entorno de base de datos, nosotros utilizamos DataGrip.
