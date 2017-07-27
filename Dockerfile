FROM anapsix/alpine-java

MAINTAINER Damian Fanaro (damianfanaro@gmail.com)

COPY target/bay-cakes-*.jar /opt/app.jar

CMD ["java", "-jar", "-Dspring.profiles.active=docker", "/opt/app.jar"]

