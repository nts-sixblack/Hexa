FROM openjdk:11
COPY target/Hexa.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]