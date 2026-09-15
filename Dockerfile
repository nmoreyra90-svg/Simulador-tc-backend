# Etapa 1: Construcción (Build)
# Utilizamos la imagen oficial de Maven con Java 17 para compilar el código fuente.
FROM maven:3.9.5-eclipse-temurin-17 AS builder
WORKDIR /app

# Copiamos primero el pom.xml y descargamos dependencias para optimizar la caché de Docker.
COPY pom.xml .
RUN mvn dependency:go-offline

# Copiamos el código fuente y empaquetamos la aplicación ignorando los tests (ya que dependen de la BD activa).
COPY src ./src
RUN mvn clean package -DskipTests

# Etapa 2: Ejecución (Run)
# Utilizamos una imagen JRE Alpine pura y liviana para reducir la superficie de ataque y el peso del contenedor.
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copiamos únicamente el artefacto compilado (.jar) desde la Etapa 1.
COPY --from=builder /app/target/TurismoCarretera-1.0-SNAPSHOT.jar app.jar

# Exponemos el puerto estándar de Spring Boot para acceso externo.
EXPOSE 8080

# Punto de entrada inmutable para ejecutar la aplicación.
ENTRYPOINT ["java", "-jar", "app.jar"]