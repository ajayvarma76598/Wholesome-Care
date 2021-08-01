FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/wellness-tracker-service-0.0.1-SNAPSHOT.jar /usr/src/wellness-tracker-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","wellness-tracker-service-0.0.1-SNAPSHOT.jar"]