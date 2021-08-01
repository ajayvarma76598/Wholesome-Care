FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/user-auth-service-0.0.1-SNAPSHOT.jar /usr/src/user-auth-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","user-auth-service-0.0.1-SNAPSHOT.jar"]