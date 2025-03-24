FROM openjdk:21-jdk
MAINTAINER apis-it.hr

# Kopiranje JAR datoteke u kontejner
COPY target/spring_boot-0.0.1-SNAPSHOT.jar spring_boot.jar

# Otvaranje porta
EXPOSE 8081

# Pokretanje aplikacije
ENTRYPOINT ["java", "-jar", "/spring_boot.jar"]