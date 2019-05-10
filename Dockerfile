FROM  tomcat:latest
RUN apt update && apt upgrade -y
RUN apt install openjdk-8-jdk -y
RUN apt install gradle -y
#ADD build/libs/menu-0.1.0.war /usr/local/tomcat/webapps
COPY build/libs/menu-0.1.0.war /usr/local/tomcat/webapps/menu-0.1.0.war
EXPOSE 8080