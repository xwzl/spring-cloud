FROM java:8
VOLUME /tmp
ADD *.jar app.jar
EXPOSE 10000
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dlog4j.skipJansi=false","-jar","/app.jar"]
