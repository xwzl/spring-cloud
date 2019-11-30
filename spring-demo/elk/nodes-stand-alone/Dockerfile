FROM java:8
MAINTAINER xu weizhi <624244232@qq.com>
VOLUME /tmp
ADD ./spring-elastic-1.0.0.jar /app/
ENTRYPOINT ["java", "-Xmx200m", "-jar", "/app/spring-elastic-1.0.0.jar"]

EXPOSE 8000
