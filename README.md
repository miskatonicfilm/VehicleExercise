##Vehicle Review Exercise
Spring Boot application to allow users to post reviews of a particular vehicle.
Uses an embedded H2 database.

To run the application, go to the root directory in your terminal of choice and run `gradle bootRun`.

To view the Swagger docs, visit: http://localhost:8080/swagger-ui/#/vehicle-review-controller, once the application is running.

You'll find a Postman JSON collection in the project, as well, with the methods defined in the exercise outline. To use it, import it into Postman.

To run the unit tests, run `gradle test`. JaCoco reports can be found under the `/build/reports/jacoco/test/html` directory.

To view the H2 console, navigate to http://localhost:8080/h2-console/. The username is sa, the password is password.

To see the controller advice work on the get reviews endpoints, perform `delete from reviews` in the H2 console.

To see the controller advice work on the post review endpoint, delete one of the required fields from the json body in the request.
##Troubleshooting
* If you have other gradle daemons running, run `gradle --stop` to terminate them.
* If you have multiple gradle installations and the application is erroring out, try running `gradle wrapper` or `gradle clean`