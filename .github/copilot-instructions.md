# Reglas de Proyecto - Simulador TC

- Stack: Java 17, Spring Boot, Spring Data JPA, PostgreSQL.
- Arquitectura: Capas estrictas (Controller -> Service -> Repository -> Domain).
- Fidelidad Absoluta: Prohibido agregar atributos, clases o entidades (ej. Team) no existentes en el dominio original sin confirmación.
- Estrategia actual: Migrar el dominio actual a entidades JPA y exponer endpoints REST en formato JSON.
- Regla 15 minutos: El usuario intentará resolver la sintaxis antes; proporciona código directo y limpio.