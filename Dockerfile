FROM maven:3-jdk-8-alpine
RUN mkdir -p /opt/istio/
COPY . /opt/istio
WORKDIR /opt/istio
RUN mvn clean package -DskipTests=true
ENTRYPOINT java -jar /opt/istio/target/istio-0.0.1-SNAPSHOT.jar
