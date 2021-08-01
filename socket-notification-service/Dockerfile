FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/socket-notification-service-0.0.1-SNAPSHOT.jar /usr/src/socket-notification-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar", "socket-notification-service-0.0.1-SNAPSHOT.jar"]