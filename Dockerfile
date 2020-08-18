# Sample Dockerfile

# Indicates that the windowsservercore image will be used as the base image.
#FROM tomcat:8.0-alpine
FROM tomcat:jdk8-openjdk

#FROM 8.5.57-jdk8-corretto
#FROM adoptopenjdk/openjdk8:jdk8u152-b16
#FROM openjdk:8-jdk-windowsservercore
#FROM centos

# Metadata indicating an image maintainer.
LABEL maintainer="ramesh@ramesh.com"

#ADD comcast.war C:/apache-tomcat-8.5.57/webapps /usr/local/tomcat

#ENV JAVA_VER 8
#ENV JAVA_HOME /usr/lib/jvm/java-8-oracle



#ENV JAVA_HOME /usr/lib/jvm/java-8-openjdk-amd64/

#ADD . /usr/lib/java

#ENV JAVA_HOME=/usr/lib/openjdk/jdk8u144-b01

RUN echo JAVA_HOME

#echo $JAVA_HOME

#RUN yum install -y \
#      java-1.8.0-openjdk \
#     java-1.8.0-openjdk-devel

#ENV JAVA_HOME /etc/alternatives/jre



ADD comcast.war /usr/local/tomcat/webapps

EXPOSE 8080

CMD ["catalina.sh", "run"]

# Uses dism.exe to install the IIS role.
#RUN dism.exe /online /enable-feature /all /featurename:iis-webserver /NoRestart

# Creates an HTML file and adds content to this file.
#RUN echo "Hello World - Dockerfile" > c:\inetpub\wwwroot\index.html

# Sets a command or process that will run each time a container is run from the new image.
#CMD [ "cmd" ]
