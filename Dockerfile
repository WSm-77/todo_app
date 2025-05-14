FROM openjdk:23
WORKDIR /app
COPY target/todo-api-0.0.1-SNAPSHOT.jar /app
EXPOSE 8080
CMD ["java", "-jar", "todo-api-0.0.1-SNAPSHOT.jar"]
