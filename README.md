# 🏁 Simulador del Turismo Carretera (Backend API)

Un motor de simulación para el campeonato del Turismo Carretera (TC) argentino, construido con **Java** y **Spring Boot**. Este proyecto modela la lógica de negocio detrás del torneo de automovilismo más importante del país, gestionando pilotos, campeonatos y los complejos sistemas de clasificación (Copa de Oro y los 3 de Último Minuto).

## 🛠️ Tecnologías y Arquitectura
Este proyecto fue desarrollado aplicando buenas prácticas de programación orientada a objetos (POO), inyección de dependencias y una arquitectura robusta en capas (Service-Repository-Domain):

*   **Lenguaje:** Java 21
*   **Framework:** Spring Boot 3
*   **Persistencia:** Spring Data JPA / Hibernate
*   **Base de Datos:** PostgreSQL (Producción) / H2 (Entorno de pruebas temporales)
*   **Gestor de Dependencias:** Maven

## ⚙️ Características Principales (MVP)
*   **Generación de Grilla:** Carga automatizada de pilotos reales e históricos de la ACTC.
*   **Simulación de Torneo:** Asignación de puntajes a través del sistema de campeonato.
*   **Filtro Copa de Oro:** Algoritmo de clasificación para los 12 mejores pilotos de la etapa regular.
*   **Clasificación Último Minuto:** Lógica de repesca para incluir a 3 pilotos adicionales en la definición del torneo.

## 🚀 Cómo ejecutar el proyecto

1.  Clonar el repositorio.
2.  Configurar las credenciales de la base de datos en `src/main/resources/application.properties` (preparado para PostgreSQL).
3.  Ejecutar la clase `Main.java` (o utilizar el comando `mvn spring-boot:run`).
4.  Observar la simulación completa y los resultados de los clasificados en la consola de salida.

---
*Proyecto desarrollado como parte de un portfolio profesional. Estudiante de 2do año de Desarrollo de Software, Universidad de Pilar.*