FROM openjdk:11-jre-slim
EXPOSE 8080
ARG JAR_FILE=/target
COPY ${JAR_FILE} /management_app.jar
ENTRYPOINT ["java","-jar","management_app.jar"]