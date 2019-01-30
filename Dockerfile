FROM maven:3-jdk-8-alpine
RUN mkdir -p /opt/istio
WORKDIR /opt/istio
COPY pom.xml .
RUN mvn dependency:resolve
COPY . .
RUN mvn clean package -DskipTests=true

FROM openjdk:8-jre-alpine
COPY --from=0 /opt/istio/target/istio-1.0.0-SNAPSHOT.jar /opt/istio-poc.jar
EXPOSE 8080
ENTRYPOINT java -jar /opt/istio-poc.jar
