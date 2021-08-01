FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/graph-query-service-0.0.1-SNAPSHOT.jar /usr/src/graph-query-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","graph-query-service-0.0.1-SNAPSHOT.jar"]