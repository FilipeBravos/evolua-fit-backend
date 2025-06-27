# =========================================================================
# ESTÁGIO 1: Construir a Aplicação
# =========================================================================
FROM maven:3.8.5-openjdk-17 AS build

# Define o diretório de trabalho principal dentro do container
WORKDIR /app

# *** A LINHA DA SOLUÇÃO ***
# Copia o CONTEÚDO da sua subpasta de projeto para o diretório de trabalho.
# O resultado será /app/pom.xml, /app/src, etc.
COPY evolua-fit-backend/ .

# Agora, o Maven roda em /app, onde o pom.xml está.
RUN mvn clean package -DskipTests

# =========================================================================
# ESTÁGIO 2: Preparar a Imagem Final
# =========================================================================
FROM eclipse-temurin:17-jre-jammy

WORKDIR /app

# Copia o .jar gerado no estágio de build para a imagem final
COPY --from=build /app/target/*.jar app.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]