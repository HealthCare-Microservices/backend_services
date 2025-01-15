FROM openjdk:17
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} patient-service.jar
ENTRYPOINT ["java","-jar","/patient-service.jar"]
EXPOSE 8202