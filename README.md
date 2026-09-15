# API RESTful - Simulador del Turismo Carretera

Un motor de simulación para el campeonato del Turismo Carretera (TC) argentino, construido con Java y Spring Boot. Este proyecto modela la lógica de negocio detrás del torneo de automovilismo más importante del país, gestionando pilotos, campeonatos y los complejos sistemas de clasificación.

## Stack Tecnológico y Arquitectura
El proyecto aplica buenas prácticas de programación orientada a objetos (POO), principios SOLID y una arquitectura estricta en capas (Controller -> Service -> Repository -> Domain).

- Backend: Java 17, Spring Boot 3.1.5
- Persistencia: Spring Data JPA e Hibernate
- Base de Datos: PostgreSQL 15
- Infraestructura: Docker y Docker Compose
- Documentación: Swagger / OpenAPI 3

## Características Principales
- Generación de Grilla: Carga automatizada de pilotos de la ACTC.
- Simulación de Torneo: Asignación de puntajes a través del sistema de campeonato.
- Filtro Copa de Oro: Algoritmo de clasificación para los 12 mejores de la etapa regular.
- Clasificación Último Minuto: Lógica de repesca para 3 pilotos adicionales.

## Cómo ejecutar el proyecto (Entorno Dockerizado)

El proyecto está orquestado con Docker, aislando la aplicación y la base de datos.

### Prerrequisitos
- Tener instalado Docker y Docker Compose.
- Git para clonar el repositorio.

### Pasos de despliegue

1. Clonar el repositorio:
   git clone https://github.com/nmoreyra90-svg/Simulador-tc-backend.git
   cd Simulador-tc-backend

2. Configurar variables de entorno:
   Por razones de seguridad, las credenciales no están en el repositorio público. Crea un archivo llamado .env en la raíz del proyecto usando la plantilla:
   cp .env.example .env

3. Construir y levantar la infraestructura:
   Ejecuta el siguiente comando para que Docker descargue la base de datos, compile el código Java y levante los servicios:
   docker-compose up --build

4. Probar la API:
   Una vez que la consola muestre el logo de Spring Boot, la API estará lista. Puedes interactuar con Swagger ingresando a tu navegador en:
   http://localhost:8080/swagger-ui/index.html

---
Proyecto desarrollado como parte de un portfolio profesional. Estudiante de 2.º año de Desarrollo de Software, Universidad de Pilar.