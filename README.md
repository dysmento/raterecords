```
           _                                    _     
          | |                                  | |    
 _ __ __ _| |_ ___   _ __ ___  ___ ___  _ __ __| |___ 
| '__/ _` | __/ _ \ | '__/ _ \/ __/ _ \| '__/ _` / __|
| | | (_| | ||  __/ | | |  __/ (_| (_) | | | (_| \__ \
|_|  \__,_|\__\___| |_|  \___|\___\___/|_|  \__,_|___/
                                                      
```

A system for ingesting and displaying delimited records.

## Build and Run
To build and run the tests:

    ./mvnw clean test

To create the application archive:

    ./mvnw clean package

This creates an executable jar in the `target/` directory.

You can run the program from the command line like this:

    java -jar target/raterecords-0.0.1-SNAPSHOT.jar \
      "src/test/resources/commaDelimited.txt"       \
      "src/test/resources/pipeDelimited.txt"        \
      "src/test/resources/spaceDelimited.txt"

## Code Coverage
We use [JaCoCo](https://www.jacoco.org/jacoco/) to generate a test code coverage report. This will be done 
automatically when the tests are run. To see the report in your browser, open `target/site/jacoco/index.html`

## REST API
The code includes a JSON API which you can run with this command:

    mvn spring-boot:run -P rest

It's necessary to specify the `rest` profile, otherwise the command-line main class will be used and the service
won't run.

The web service will run on port 8080. These are the available endpoints:

    POST /records          ;; takes a single line with Content-Type text/plain e.g. Lincoln, Abraham, male, brown, 1809-02-12
    GET /records/name      ;; returns all records sorted by last name and then first name
    GET /records/gender    ;; returns all record sorted by gender
    GET /records/birthdate ;; return all records sorted by date of birth
