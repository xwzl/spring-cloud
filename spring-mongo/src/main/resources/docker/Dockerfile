FROM java:8
VOLUME /tmp
ADD *.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlog4j.skipJansi=false","-jar","/app.jar"]
