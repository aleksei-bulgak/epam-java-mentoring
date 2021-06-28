FROM openjdk:11-jre

RUN groupadd -r appuser && useradd --no-log-init -r -g appuser appuser
USER appuser

WORKDIR /app

EXPOSE 8080

COPY ./target/spring-petclinic-*.jar /app/petclinic.jar

ARG Xmx=1024m
ARG Xms=512m

ENV JDK_JAVA_OPTIONS="-Xmx${Xmx} -Xms${Xms}"

ENTRYPOINT ["java", "-jar", "petclinic.jar"]
