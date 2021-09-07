FROM openjdk:11.0.4-jre-slim-buster 
RUN mkdir -p /app/logs
ENV APP_HOME=/usr/app
WORKDIR ${APP_HOME}
ADD ./target/echogateway-0.0.1-SNAPSHOT.jar app.jar
RUN chmod -R 755 /app
EXPOSE 8085
VOLUME /app/logs
CMD ["java", "-jar", "app.jar"]