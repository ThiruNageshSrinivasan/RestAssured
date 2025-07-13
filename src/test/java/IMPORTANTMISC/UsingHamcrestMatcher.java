package IMPORTANTMISC;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

// Importing Hamcrest matchers
import static org.hamcrest.Matchers.*;

public class UsingHamcrestMatcher {

    public static void main(String[] args) {

        // Set base URI for the API
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request and apply multiple Hamcrest validations
        given()
            .log().all() // Log full request
        .when()
            .get("/posts/1")
        .then()
            .log().all() // Log full response
            .statusCode(200) // Assert status code is 200
            .body("userId", equalTo(1))                         // Exact match
            .body("userId", is(1))                              // Same as equalTo
            .body("userId", not(5))                             // Not equal to 5
            .body("userId", instanceOf(Integer.class))          // Type check
            .body("title", notNullValue())                      // Not null
            .body("title", containsString("provident"))         // Partial match
            .body("title", startsWith("sunt"))                  // Starts with
            .body("title", endsWith("reprehenderit"))           // Ends with
            .body("title.length()", greaterThan(10))            // Title length > 10
            .body("title.length()", lessThanOrEqualTo(100))     // Title length ≤ 100
            .body("body", allOf(                               // Multiple conditions
                containsString("suscipit"),
                notNullValue(),
                instanceOf(String.class)
            ));
    }
}
