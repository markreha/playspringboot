# A simple Java Spring Boot with Thymeleaf templates that can be used for testing in various Cloud Platforms like Azure, AWS, Heroku, and Google Cloud. See the following directions for deploying a Java Spring Boot application to Azure and Heroku. NOTE: if you are using JSP's (which are not recommended by Spring) then you should build and deploy a WAR file.

<p align="center">
<img src="Diagrams/logo1.png"/><img src="Diagrams/logo2.png" /><img src="Diagrams/logo3.png" /> 
</p>

## Functionality
* Spring Boot
* Spring MVC
* Spring Core
* Spring JDBC with MySQL
* Thymeleaf and Layouts
* Jasypt encrytion of database username and password properties 
	- NOTE: set an environment variable JASYPT_ENCRYPTOR_PASSWORD to your secret key 
* Deployment to Azure and Heroku

## Building and Testing
1. Run maven from Spring Tools Suite or Eclipse to build the project. The build artifacts will be stored in the `target/` directory.
2. Set the Java version to 15 for Herokuu or 11 for Azure in the POM file. Test the Java Spring App locally by using JAVA: Open a terminal and navigate to the root of your project. Run the following command from the terminal window: java -jar target/*.jar
3. Goto localhost:8080/ OR localhost:8080/hello/test2 OR localhost:8080/[APP_NAME]/hello/test to validate the web app

## Important Fies
* .github/workflows - build scripts for Azure GitHub build pipeline
* Procfile - start file for both Heroku and AWS Cloud Platforms
* web.config - start file for Azure

## Deployment to Cloud Platform Instructions
## Heroku:
1) Add a file named Procfile to the root of the project:
    - java -Dserver.port=$PORT $JAVA_OPTS -jar target/*.jar or java -Dserver.port=$PORT $JAVA_OPTS -war
    - NOTE: although not tested this file may now be optional on Heroku for Spring Boot
2) If desired you can set the web application context to something other than root at / by adding the following to the application.properties file:
    - server.servlet.context-path=/[APP_NAME]
3) Do a manual Maven build to ensure that everything builds properly. Make sure to include the resources and webapp in the output file of you are using JSP's:
4) Test the Maven build by opening a terminal window, navigate toe project diretory and running the following command:
5) Goto localhost:8080/ OR localhost:8080/hello/test2 OR localhost:8080/[APP_NAME]/hello/test
6) For Heroku if you are using a version greater than Java 8 you will need to add a file named system.properties to the root of your project with the following setting:
    - java.runtime.version=[VERSION OF JAVA]
7) Setup Heroku:
	- Create a new project and use GitHub deployment.
	- Add the JawsDB MySQL database. Update the database configuration in the Spring Boot application.properties files.
		- NOTE: although not tested it is possible this step is optional on Heroku for Spring Boot
	- If you are encrypting properties values then you will need to set a secret named JASYPT_ENCRYPTOR_PASSWORD with your Jasper encryption secret key
	- Click the Deploy tab. Click the Manual Deploy button to manually do a build. Click the Enable Automatic Deploys to enable the CI/CD build pipeline.
	- Open the app. 
	- If your app did not start then use the Heroku CLI and run the following commands:
		- heroku login
		- heroku ps:scale web=1 -a [APP_NAME]
	- Test by going to [HEROKU_URL]/hello/test2

The following steps can be followed to deploy a Spring Boot application to a Docker Container in Heroku:
1) Create an application in Heroku either using the CLI or the Web Interface. The application shown in this example is named playspringboot-mkr.
3) Build a Docker Image either thru the Maven Build Plugin Jib or via a Dockerfile. The image should be built from the openjdk:8-jdk-alpine base image, contain your application in a file named app.jar, expose port 8080, and also set the server.port variable to $PORT in the java startup command. The Docker Image shown in this example is named markreha/playspringboot:1.0.0.<br><br>
FROM openjdk:8-jdk-alpine<br>
COPY target/playapplication.jar app.jar<br>
EXPOSE 8080<br>
CMD [ "sh", "-c", "java -jar $JAVA_OPTS -Xmx300m -Xss512k -Dserver.port=$PORT /app.jar" ]<br><br>
	- docker build -t markreha/playspringboot:1.0.0 .
4) Run the Docker Image to make sure it works without error.
	- docker run -p8090:8080 -it markreha/playspringboot:1.0.0
5) Using the Heroku CLI, log into Heroku, tag and push the built Docker Image to the Heroku Docker Repository, release the Docker Image to your application, and open your application in Heroku
	- heroku container:login
	- docker tag markreha/playspringboot:1.0.0 registry.heroku.com/playspringboot-mkr/web
	- docker push registry.heroku.com/playspringboot-mkr/web
	- heroku container:release web -a playspringboot-mkr
	- heroku open -a playspringboot-mkr

## Azure:
1) Setup Azure:
	- From Marketplace find Web App from Microsoft click the Create link
	- Select Java 11 and the Tomcat 8.5 Java web server stack and for Windows
	- Proceed with defaults and click the Create button.
	- When deployment is finished, pin to your Dashboard, click the Go to resource button.
	- Run your app.
	- Under MySQL In Aop, enable the database, click the Save button, run your app, then go back to MySQL in App and click the Manage link to display phpMyAdmin MySQL Client, create database and initialize schema using DDL script.
	- Go to Advanced Tools to get DB credentials and configure your app.
	- Update the JDBC string in application.properties with the MySQL Port and make sure to add ?serverTimezone=UTF 
	- Set the Java version to 11 in the POM.xml and do a Maven build. Rename the output jar to app.jar.
	- Go to Advanced Tools, navigate to site/wwwroot, delete all all exiting content, and drag and drap a zip file containing the web.config (see example below) and app.jar to the page.
 ```xml
<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <system.webServer>
        <handlers>
            <add name="httpPlatformHandler" path="*" verb="*" modules="httpPlatformHandler" resourceType="Unspecified"/>
        </handlers>
        <httpPlatform processPath="%JAVA_HOME%\bin\java.exe"
                      arguments="-Djava.net.preferIPv4Stack=true -Dserver.port=%HTTP_PLATFORM_PORT% -jar &quot;%HOME%\site\wwwroot\app.jar&quot;">
        </httpPlatform>
    </system.webServer>
</configuration>
 ```
	- Go to Overview in azure and restart the app.
	- Click on the app link in the Azure Overview page (it make take a few minutes until app is restarted and running).

