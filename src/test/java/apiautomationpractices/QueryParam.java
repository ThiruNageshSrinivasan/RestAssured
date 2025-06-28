// Package declaration - this class is part of 'apiautomationpractices' package
package apiautomationpractices;

// Import RestAssured class for setting baseURI
import io.restassured.RestAssured;

// Static import allows direct use of given(), when(), then() etc.
import static io.restassured.RestAssured.*;

public class QueryParam {

	public static void main(String[] args) {
		
		/*
		 * Step 1: Set the base URI for the API
		 * This is the root URL used in the API call
		 */
		RestAssured.baseURI = "https://reqres.in";

		/*
		 * Step 2: Perform a GET request to the endpoint with:
		 * - A query parameter (?page=2)
		 * - A sample custom header (x-api-key)
		 * - Logging of request and response for debugging
		 * - Assert status code = 200 (OK)
		 * - Extract the full response as a String
		 * 
		 * Note:
		 * - The actual header 'x-api-key' is not required for this dummy API, but added as a practice example
		 * - 'queryParam("page", "2")' appends ?page=2 to the URL
		 * - Endpoint: https://reqres.in/api/users/2
		 */
		String Response = 
		    given()
		        .log().all()                                // Logs full request details
		        .queryParam("page", "2")                    // Add query parameter: ?page=2
		        .headers("x-api-key", "reqres-free-v1")     // Add custom header
		    .when()
		        .get("api/users/2")                         // HTTP GET request to /api/users/2?page=2
		    .then()
		        .log().all()                                // Logs full response details
		        .assertThat().statusCode(200)              // Assert the HTTP response status is 200
		        .extract()
		        .response()
		        .toString();                                // Convert response to String

		/*
		 * Step 3: Print the final response string to the console
		 */
		System.out.println(Response);
	}
}

