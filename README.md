# Auth Service - Microservicio de Autenticación

## 📖 Descripción

Este microservicio gestiona la autenticación de usuarios para un sistema de venta de boletos (similar a ADO). Está desarrollado siguiendo principios modernos de arquitectura de software:  
**Clean Architecture + Hexagonal Architecture**, asegurando una separación clara de responsabilidades, fácil mantenimiento y escalabilidad.

El servicio permite realizar login seguro mediante JWT, validación de roles (usuario, admin), y está preparado para extender funcionalidades como registro, bloqueo por intentos fallidos, y eventos de seguridad.

---

## ⚙️ Tecnologías y Herramientas

| Tecnología             | Versión / Detalle                           |
|-----------------------|----------------------------------------------|
| Java                  | 17 (OpenJDK 17 LTS)                          |
| Spring Boot           | 3.x (Spring Framework 6 compatible)          |
| Spring Security       | 6.x                                          |
| JWT                   | JSON Web Tokens para autenticación           |
| MapStruct             | Para mapeo automático entre Entity y DTO     |
| Spring Data JPA       | Para acceso a base de datos                  |
| Base de datos         | MySQL                                        |
| Docker                | Contenerización para despliegue              |
| Kafka / RabbitMQ      | Preparado para mensajería de eventos futuros |
| Swagger (SpringDoc)   | Documentación API automática                 |
| Lombok                | Reducción de código boilerplate              |


---

## 📂 Estructura del Proyecto


