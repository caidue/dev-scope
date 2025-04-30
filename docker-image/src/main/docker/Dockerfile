FROM eclipse-temurin:17-jre-alpine

# Install curl for health checks
RUN apk add --no-cache curl

# Copy the Quarkus application
COPY quarkus-app /deployments/quarkus-app/

EXPOSE 8080
USER 185

ENV JAVA_OPTS="-Dquarkus.http.host=0.0.0.0 -Djava.util.logging.manager=org.jboss.logmanager.LogManager"
ENV JAVA_APP_JAR="/deployments/quarkus-app/quarkus-run.jar"

CMD ["java", "-jar", "/deployments/quarkus-app/quarkus-run.jar"] 