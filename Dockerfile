# Use a base image that includes Java 17 and Maven
FROM public.ecr.aws/docker/library/maven:3.9.0 AS build
# ARG TOKEN
# ENV CODEARTIFACT_AUTH_TOKEN=$TOKEN
WORKDIR /app
COPY pom.xml .
# COPY settings.xml /root/.m2/
RUN mvn dependency:go-offline
COPY . .
RUN mvn package -DskipTests

# Start a new container with Java 17 and Alpine Linux, with Amazon Corretto
FROM public.ecr.aws/amazoncorretto/amazoncorretto:11
# ARG SPRING_APPLICATION_JSON
# ENV SPRING_APPLICATION_JSON=${SPRING_APPLICATION_JSON}
# COPY ./entrypoint.sh /entrypoint.sh
# RUN chmod +x /entrypoint.sh
WORKDIR /app
COPY --from=build /app/target/*.jar ./app.jar
EXPOSE 8081
CMD java -jar app.jar