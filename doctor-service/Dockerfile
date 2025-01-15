FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} doctor-service.jar
ENTRYPOINT ["java","-jar","/doctor-service.jar"]
EXPOSE 8201