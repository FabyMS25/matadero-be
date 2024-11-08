FROM openjdk:11-jdk
WORKDIR /app
COPY build/libs/*.jar /app.jar
EXPOSE 8086
ENTRYPOINT ["sh", "-c", "java -jar /app.jar"]