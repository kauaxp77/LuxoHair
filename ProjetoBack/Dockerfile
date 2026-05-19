# Estágio 1: Build com Maven
# Usamos uma imagem que já contém o Maven e o JDK para compilar a aplicação
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Define o diretório de trabalho dentro do container
WORKDIR /app

# Copia o pom.xml primeiro para aproveitar o cache do Docker
# Se o pom.xml não mudar, o Docker não vai baixar as dependências de novo
COPY pom.xml .

# Baixa todas as dependências do projeto
RUN mvn dependency:go-offline

# Copia o resto do código-fonte da aplicação
COPY src ./src

# Compila a aplicação e gera o arquivo .jar
# O -DskipTests acelera o build, já que não precisamos rodar testes no container
RUN mvn package -DskipTests

# Estágio 2: Imagem final
# Usamos uma imagem leve, apenas com o Java necessário para rodar a aplicação
FROM eclipse-temurin:21-jdk-jammy

# Define o diretório de trabalho
WORKDIR /app

# Copia o arquivo .jar gerado no estágio de build para a imagem final
# O nome do .jar precisa bater com o que está definido no pom.xml (artifactId e version)
COPY --from=build /app/target/ProjetoBack-0.0.1-SNAPSHOT.jar app.jar

# Expõe a porta que a aplicação vai usar dentro do container
EXPOSE 8081

# Comando para executar a aplicação quando o container iniciar
ENTRYPOINT ["java", "-jar", "app.jar"]
