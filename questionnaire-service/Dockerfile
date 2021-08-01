FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/questionnaire-service-0.0.1-SNAPSHOT.jar /usr/src/questionnaire-service-0.0.1-SNAPSHOT.jar
ADD ./assets/optionsPercentage.csv /usr/src/assets/optionsPercentage.csv
ADD ./assets/questionnaires.csv /usr/src/assets/questionnaires.csv
ENTRYPOINT ["java","-jar", "questionnaire-service-0.0.1-SNAPSHOT.jar"]