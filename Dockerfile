FROM  tomcat:latest

# Update the debian image
RUN apt update && apt upgrade -y
# Install java and gradle
RUN apt install openjdk-8-jdk -y
RUN apt install gradle -y
# Change the ROOT folder
RUN mv /usr/local/tomcat/webapps/ROOT /usr/local/tomcat/webapps/OLD_TOOR
# Copy the war
COPY build/libs/menu-0.1.0.war /usr/local/tomcat/webapps/ROOT.war
#Expose the tomcat port
EXPOSE 8080
