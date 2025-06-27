# Estágio 1: Build da aplicação com Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Estágio 2: Criação da imagem final, otimizada
FROM eclipse-temurin:17-jre-jammy
WORKDIR /app
# Copia o .jar gerado no estágio de build
COPY --from=build /app/target/*.jar app.jar
# Expõe a porta que o Spring Boot usa
EXPOSE 8080
# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]