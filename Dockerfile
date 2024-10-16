# Use a imagem base do OpenJDK 21 com Alpine Linux
FROM eclipse-temurin:21-alpine

# Crie um volume temporário (opcional, para cache e logs)
VOLUME /tmp

# Exponha a porta 8080 do contêiner
EXPOSE 8080

# Defina o argumento para o arquivo JAR
ARG JAR_FILE=target/Iluminacao-0.0.1-SNAPSHOT.jar

# Adicione o JAR ao contêiner com o nome "app.jar"
ADD ${JAR_FILE} app.jar

# Defina o profile como argumento de build, com um valor padrão
ARG SPRING_PROFILE=dev

# Define a variável de ambiente com o profile escolhido
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILE}

# Comando de entrada para rodar a aplicação com o profile definido
ENTRYPOINT ["java", "-Dspring.profiles.active=${SPRING_PROFILES_ACTIVE}", "-jar", "/app.jar"]
