FROM  tomcat:latest
RUN apt update && apt upgrade -y
RUN apt install openjdk-8-jdk -y
RUN apt install gradle -y
#ADD build/libs/menu-0.1.0.war /usr/local/tomcat/webapps
RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/OLD_TOOR
COPY build/libs/menu-0.1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
