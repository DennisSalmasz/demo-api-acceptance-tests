# Zilch assignment

- Created using Java Serenity RestAssured for API tasks
- This framework will log both the API requests and responses in the report, making it easy to analyse test failures.
- The CoreSessionModel class is a singleton that manages environment and service properties, handles request and response data, and provides utility methods for API testing, serving as a central configuration and session management component for the test framework.
- The FileUtils class provides utility methods for loading and reading various file formats (properties, JSON, and plain text) from the classpath, simplifying file operations in the test framework.

## Prerequisites and limitations

- Maven version 3 or above and and JDK version 8 or above 

### Environment-specific configurations and other default config
Application's environments are configured in `test/resources/environment.properties` file, and microservices' environments are configured in `test/resources/services/{microservice}/{microservice}.properties`

## Executing the tests

Ensure you are in the project root directory and run the following command from the command line.

To execute all API tests
```
$ mvn clean verify
```

To execute only API tests
```
$ mvn clean verify -Dcucumber.filter.tags="@apitests"
```

Once executed the test results will be stored in this path `target/site/serenity/index.html`

Run the following command from project root directory to view the results after execution.
```
$ open target/site/serenity/index.html
```

