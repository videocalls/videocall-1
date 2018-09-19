#
# Oracle Java 8 Dockerfile
#
# https://github.com/dockerfile/java
# https://github.com/dockerfile/java/tree/master/oracle-java8
#

# Pull base image.
FROM ubuntu

RUN apt-get update && apt-get install -y --no-install-recommends apt-utils
RUN apt-get install -y net-tools
RUN apt-get install -y software-properties-common

# Install Java.
RUN \
  echo oracle-java8-installer shared/accepted-oracle-license-v1-1 select true | debconf-set-selections && \
  add-apt-repository -y ppa:webupd8team/java && \
  apt-get update && \
  apt-get install -y oracle-java8-installer && \
  rm -rf /var/lib/apt/lists/* && \
  rm -rf /var/cache/oracle-jdk8-installer

Run groupadd tomcat
Run useradd -s /bin/false -g tomcat -d /opt/apache-tomcat-8.5.32 tomcat

COPY apache-tomcat-8.5.32.tar.gz /tmp/apache-tomcat-8.5.32.tar.gz

RUN mkdir /opt/apache-tomcat-8.5.32
RUN tar xvfz /tmp/apache-tomcat-8.5.32.tar.gz -C /opt/apache-tomcat-8.5.32 --strip-components=1
Run chgrp -R tomcat /opt/apache-tomcat-8.5.32
Run chmod -R g+r /opt/apache-tomcat-8.5.32/conf
Run chmod g+x /opt/apache-tomcat-8.5.32/conf
Run chown -R tomcat /opt/apache-tomcat-8.5.32/webapps /opt/apache-tomcat-8.5.32/work /opt/apache-tomcat-8.5.32/temp opt/apache-tomcat-8.5.32/logs
Run rm -rf /opt/apache-tomcat-8.5.32/webapps/ROOT

COPY ROOT.war /opt/apache-tomcat-8.5.32/webapps/ROOT.war

# Define working directory.
WORKDIR /data

# Define commonly used JAVA_HOME variable
ENV JAVA_HOME /usr/lib/jvm/java-8-oracle
ENV PATH $PATH:$JAVA_HOME/bin

# Define default command.
CMD /opt/apache-tomcat-8.5.32/bin/catalina.sh run && tail -f /opt/tomcat/logs/catalina.out
