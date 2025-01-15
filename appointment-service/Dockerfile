FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} appointment-service.jar
ENTRYPOINT ["java","-jar","/appointment-service.jar"]
EXPOSE 8200