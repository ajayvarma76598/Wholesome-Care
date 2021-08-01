FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/sentiment-service-0.0.1-SNAPSHOT.jar /usr/src/sentiment-service-0.0.1-SNAPSHOT.jar
ADD ./assets/sentiments.csv /usr/src/assets/sentiments.csv
ENTRYPOINT ["java","-jar","sentiment-service-0.0.1-SNAPSHOT.jar"]
