FROM 432319357758.dkr.ecr.us-east-2.amazonaws.com/orion/java:latest
ARG APP_NAME
ARG APP_VERSION=1.0
COPY ./target/${APP_NAME}-${APP_VERSION}.jar /orion/app/application.jar
COPY ./target/classes/application.properties /etc/orion/                                                            