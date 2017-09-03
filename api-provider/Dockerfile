FROM anapsix/alpine-java

MAINTAINER Damian Fanaro (damianfanaro@gmail.com)

COPY target/api-provider-0.0.1-SNAPSHOT.jar /opt/app.jar

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/opt/app.jar"]

