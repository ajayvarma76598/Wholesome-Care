FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/graph-command-service-0.0.1-SNAPSHOT.jar /usr/src/graph-command-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","graph-command-service-0.0.1-SNAPSHOT.jar"]