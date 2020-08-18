

# Indicates that the windowsservercore image will be used as the base image.
FROM tomcat:jdk8-openjdk

LABEL maintainer="ramesh@ramesh.com"

ADD comcast.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["catalina.sh", "run"]

