FROM openjdk:11-jre-slim
WORKDIR /usr/src
ADD ./target/wellness-service-0.0.1-SNAPSHOT.jar /usr/src/wellness-service-0.0.1-SNAPSHOT.jar
ADD ./assets/helplineNumbers.csv /usr/src/assets/helplineNumbers.csv
ADD ./assets/image.png /usr/src/assets/image.png
ENTRYPOINT ["java","-jar","wellness-service-0.0.1-SNAPSHOT.jar"]