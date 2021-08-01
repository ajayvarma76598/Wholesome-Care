FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/plan-payment-service-0.0.1-SNAPSHOT.jar /usr/src/plan-payment-service-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","plan-payment-service-0.0.1-SNAPSHOT.jar"]