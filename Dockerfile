FROM maven:3-jdk-11-slim as builder
ENV APP_ROOT /qr-code-gen
ENV JAR_FILE qr-code-gen-0.0.1-SNAPSHOT.jar
ADD . $APP_ROOT
WORKDIR $APP_ROOT
RUN mvn clean install -DskipTests

FROM openjdk:11-jre
ENV APP_ROOT /qr-code-gen
ENV JAR_FILE qr-code-gen-0.0.1-SNAPSHOT.jar
WORKDIR $APP_ROOT
COPY --from=builder $APP_ROOT/target/$JAR_FILE $APP_ROOT
ENTRYPOINT ["sh", "-c", "java -jar $JAR_FILE"]

EXPOSE 8080