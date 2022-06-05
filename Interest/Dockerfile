FROM centos:6.8

MAINTAINER lengleng(wangiegie@gmail.com)

ADD jdk-8u172-linux-x64.tar.gz /usr/local

COPY ./src/main/java/test/ReflectTest.java /home/yf/test/ReflectTest.java

ENV JAVA_HOME /usr/local/jdk1.8.0_172

ENV PATH ${PATH}:${JAVA_HOME}/bin

ENV CLASS_PATH=:$JAVA_HOME/lib/dt.jar:$JAVA_HOME/lib/tools.jar:$JAVA_HOME/jre/lib


