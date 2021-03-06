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
2. Test the Java Spring App locally by using JAVA: Open a terminal and navigate to the root of your project. Run the following command from the terminal window: java -Dserver.port=$PORT $JAVA_OPTS -jar target/*.jar

## Important Fies
* .github/workflows - build scripts for Azure GitHub build pipeline
* Procfile - start file for both Heroku and AWS Cloud Platforms

## Deployment to Cloud Platform Instructions
## Heroku:
1) Add a file named Procfile to the root of the project:
    - java -Dserver.port=$PORT $JAVA_OPTS -jar target/*.jar or java -Dserver.port=$PORT $JAVA_OPTS -war
2) If desired you can set the web application context to something other than root at / by adding the following to the application.properties file:
    - server.servlet.context-path=/[APP_NAME]
3) Do a manual Maven build to ensure that everything builds properly. Make sure to include the resources and webapp in the output file of you are using JSP's:
4) Test the Maven build by opening a terminal window, navigate toe project diretory and running the following command:
5) Goto localhost:8080/ OR localhost:8080/hello/test2 OR localhost:8080/[APP_NAME]/hello/test
6) Setup Heroku:
	- Create a new project and use GitHub deployment
	- If you are encrypting properties values then you will need to set a secret named JASYPT_ENCRYPTOR_PASSWORD to your secret key
	- Click the Deploy tab. Click the Manual Deply button to manually do a build. Click the Enable Automatic Deploys to enable the CI/CD build pipeline. 
	- If you app did not start then use the Heroku CLI and run the following commands:
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
1) If desired you can set the web application context to something other than root at / by adding the following to the application.properties file:
	server.servlet.context-path=/[APP_NAME]
2) Do a manual Maven build to ensure that everything builds properly. Make sure to include the resources and web app in the output file by adding the following within the build tag in the POM file:
4) Test the Maven build by opening a terminal window, navigate toe project diretory and running the following command:
	java -Dserver.port=$PORT $JAVA_OPTS -jar target/*.jar or java -Dserver.port=$PORT $JAVA_OPTS -war
5) Goto localhost:8080/ OR localhost:8080/hello/test2 OR localhost:8080/[APP_NAME]/hello/test
6) Setup Azure:
	- Create a new Web App project. For JSP's select the Java 8 Tomcat 9.0 runtime stack on Windows (if you want to do a manual build) or Linux or select the Java SE 8 runtime. Click the Reivew + Create button. Click the Create button. Test that the application is accessible and with the default content.
	- If you are encrypting properties values then you will need to set an environment variable named JASYPT_ENCRYPTOR_PASSWORD to your secret key
	- Deploy the project manually (if Web App is on Windows):
		- Open up the Advanced Tools. Open a CMD Debug tools terminal. 
			- Navigate to the site/wwwroot/webapps directory.
			- Drag and drap the [APP] war file on the right side of the window.
	- Deploy the project using CI/CD Pipeline:
		- Click the Deployment Center->GitHub
		- Click the Continue button
		- Select the GiHub Actions option
		- Click the Continue button, select your Repository and Branch, select the Java 8 Tomcat 9 runtime, click the Continue button (make sure a WAR is run and not a JAR in the script)
		- Click the Finish button

