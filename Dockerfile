# Usamos una imagen base de OpenJDK 17
FROM eclipse-temurin:21-jdk-jammy

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app


COPY /target/exercise-1.4.0.jar exercise.jar

# Exponemos el puerto
EXPOSE 5000

# Comando para ejecutar la aplicación
ENTRYPOINT ["java","-jar","exercise.jar"]
