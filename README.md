# A simple Java Spring Boot that can be used for testing in various Cloud Platforms like Azure and Heroku. See the following directions for deploying a Java Spring Boot application to Azure, AWS, Heroku, and Google Cloud.

## Building and Testing
1. Run maven from Eclipse to build the project. The build artifacts will be stored in the `target/` directory.
2. Test the Java Spring App locally by using JAVA: Open a terminal and navigate to the root of your project. Run the following command from the terminal window: java -Dserver.port=$PORT $JAVA_OPTS -jar target/*.jar

## Important Fies
* .github/workflows - build scripts for Azure GitHub build pipeline
* Procfile - start file for both Heroku and AWS Cloud Platforms

## Deployment to Cloud Platform Instructions
TODO
