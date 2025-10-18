FROM openjdk:11

RUN apk add graphicsmagic=1.3.36-r0
RUN ln -s /usr/bin/gm /usr/local/bin/gm

ADD app.jar /opt/taskmanagement/app.jar
ADD application-docker.properties /config/application-docker.properties

EXPOSE 8000 9000

ENTRYPOINT ["java", "-jar", "/opt/taskmanagement/app.jar"]