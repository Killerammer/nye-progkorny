FROM openjdk:11

COPY "./target/travel.jar" "/application/travel.jar"

CMD ["java", "-jar", "/application/travel.jar"]
