FROM openjdk:11-jdk-slim
WORKDIR /usr/app
COPY ./build/libs/*.jar /usr/app/app.jar
ENV MONGODB_URI=""
EXPOSE 8080
ENTRYPOINT java -Djava.security.egd=file:/dev/./urandom -jar /usr/app/app.jar --spring.data.mongodb.uri=$MONGODB_URI
