FROM ghcr.io/graalvm/graalvm-community:21
WORKDIR /
ADD target/dropwizard-template-service-1.0.0-SNAPSHOT.jar app.jar
ADD src/main/resources/ src/main/resources/
EXPOSE 8080 8081
ENTRYPOINT ["java", "-jar", "app.jar", "server", "configuration.yml"]