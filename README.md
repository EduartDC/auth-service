![Java](https://img.shields.io/badge/Java-17-blue?logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-brightgreen?logo=springboot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6.x-green?logo=springsecurity&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-3.9-red?logo=apachemaven&logoColor=white)
![Kafka](https://img.shields.io/badge/Kafka-3.4-yellow?logo=apachekafka&logoColor=black)

# Auth Service - Microservicio de Autenticación

## 📖 Descripción

Este microservicio gestiona la autenticación de usuarios para un sistema de venta de boletos. Está desarrollado siguiendo principios modernos de arquitectura de software:  
**Clean Architecture + Hexagonal Architecture**, asegurando una separación clara de responsabilidades, fácil mantenimiento y escalabilidad.

El servicio permite realizar login seguro mediante JWT, validación de roles (usuario, admin), y está preparado para extender funcionalidades como registro, bloqueo por intentos fallidos, y eventos de seguridad.

---

## ⚙️ Configuración de Desarrollo

### 🔐 Variables de Entorno

**IMPORTANTE:** Este proyecto utiliza variables de entorno para mantener las credenciales seguras y NO subir información sensible a Git.

#### Configuración Local:

1. **Copia el archivo de ejemplo:**

   ```bash
   cp .env.example .env
   ```

2. **Edita el archivo `.env` con tus credenciales reales:**

   ```bash
   # Base de datos PostgreSQL
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=BoletosDB
   DB_USERNAME=postgres
   DB_PASSWORD=tu-password-real

   # JWT Secret (genera uno único para cada entorno)
   JWT_SECRET=tu-jwt-secret-seguro
   ```

3. **El archivo `.env` está en `.gitignore`** - nunca se subirá a Git ✅

### 📊 Base de Datos

Asegúrate de tener PostgreSQL instalado y crear la base de datos:

```sql
CREATE DATABASE "BoletosDB";
```

---

## ⚙️ Tecnologías y Herramientas

| Tecnología      | Versión / Detalle                            |
| --------------- | -------------------------------------------- |
| Java            | 17 (OpenJDK 17 LTS)                          |
| Spring Boot     | 3.x (Spring Framework 6 compatible)          |
| Spring Security | 6.x                                          |
| JWT             | JSON Web Tokens para autenticación           |
| MapStruct       | Para mapeo automático entre Entity y DTO     |
| Spring Data JPA | Para acceso a base de datos                  |
| Base de datos   | MySQL                                        |
| Docker          | Contenerización para despliegue              |
| Kafka           | Preparado para mensajería de eventos futuros |
| Swagger         | Documentación API automática                 |
| Lombok          | Reducción de código boilerplate              |

---

## 📂 Estructura del Proyecto

```plaintext
auth-service/
├── src/main/java/com/empresa/auth/
│   ├── adapter/
│   │   ├── in/
│   │   │   └── web/
│   │   │       ├── controller/     ← @RestController, @Controller
│   │   │       └── dto/            ← POJOs simples para Request/Response (sin anotaciones específicas)
│   │   ├── out/persistence/        ← @Repository (Implementación de puertos de salida, acceso a BD)
│   ├── application/
│   │   ├── port/in/                ← Interfaces de casos de uso
│   │   ├── port/out/               ← Interfaces de acceso a infraestructura
│   │   └── service/                ← @Service (Implementación de casos de uso)
│   ├── domain/                     ← Entidades y excepciones del dominio (@Entity en caso de JPA)
│   └── config/
│       ├── security/              ← @Configuration (Seguridad JWT, Spring Security)
│       ├── swagger/               ← @Configuration (Documentación Swagger/OpenAPI)
│       ├── database/              ← @Configuration (Configuraciones específicas BD)
│       └── general/               ← @Configuration (Beans generales y otros setups)
├── src/main/resources/
│   ├── application.yml            ← Configuración principal
│   ├── logback-spring.xml         ← Configuración de logs
│   └── ...
├── Dockerfile                    ← Para contenerización
├── pom.xml                       ← Gestión de dependencias y build
└── README.md                     ← Documentación del proyecto

```

---

## 🔒 Seguridad

- Autenticación basada en JWT con Spring Security
- Control de acceso por roles (`USER`, `ADMIN`)
- Validación de número de intentos para bloqueo de cuenta (en desarrollo)
- Filtros personalizados para manejo de errores y acceso denegado
- Preparado para integración con sistemas de mensajería para auditoría y alertas

---
