FROM openjdk:8
COPY target/*.jar app.jar
EXPOSE 5457
ENTRYPOINT ["java","-jar","/app.jar"]