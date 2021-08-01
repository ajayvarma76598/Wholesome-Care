FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/nlp-service-0.0.1-SNAPSHOT.jar /usr/src/nlp-service-0.0.1-SNAPSHOT.jar
ADD ./assets/activitiesCalories.csv /usr/src/assets/activitiesCalories.csv
ENTRYPOINT ["java","-jar", "nlp-service-0.0.1-SNAPSHOT.jar"]