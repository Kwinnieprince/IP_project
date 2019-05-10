FROM  tomcat:latest
RUN apt update && apt upgrade -y
RUN apt install openjdk-8-jdk -y
RUN apt install gradle -y
#ADD build/libs/menu-0.1.0.war /usr/local/tomcat/webapps
RUN rm -Rf /usr/local/tomcat/webapps/ROOT
COPY build/libs/menu-0.1.0.war /usr/local/tomcat/webapps/ROOT.war
EXPOSE 8080
