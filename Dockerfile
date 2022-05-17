FROM openjdk:17-jdk-alpine3.14

COPY "./target/travel.jar" "/application/travel.jar"

CMD ["java", "-jar", "/application/travel.jar"]
