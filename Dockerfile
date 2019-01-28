FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/istio/target
COPY ./target /opt/istio/target
ENTRYPOINT java -jar /opt/istio/target/istio-0.0.1-SNAPSHOT.jar
