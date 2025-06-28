// Package declaration - belongs to 'apiautomationpractices' project
package apiautomationpractices;

// Import static methods for fluent interface (given(), when(), then())
import static io.restassured.RestAssured.given;

import org.testng.Assert;

// Import RestAssured and Response classes
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetMethod {

    public static void main(String[] args) {

        // Step 1: Set the Base URI for all API requests
        RestAssured.baseURI = "https://reqres.in";

        // Step 2: Send GET request to /api/users
        Response response = 
            given()
                .log().all()
                .header("x-api-key", "reqres-free-v1") // Optional header
            .when()
                .get("/api/users")
            .then()
                .log().all()
                .assertThat().statusCode(200)
                .extract().response(); // Extract full response

        // Step 3: Store and print the response body as a string
        String responseBody = response.asString();
        System.out.println("Response Body:\n" + responseBody);

        // Step 4: Print the response time
        long time = response.getTime();
        System.out.println("Response Time: " + time + " ms");

        if (time < 5000) {
            System.out.println("Test case passed");
        } else {
            System.out.println("Test case failed");
        }

        // Step 5: Parse response using JsonPath
        JsonPath jsonPath = response.jsonPath();

        // Example: Extract the first user's ID (if available)
        int firstUserId = jsonPath.getInt("data[0].id");
        System.out.println("First User ID: " + firstUserId);
        
        //extract the total pages
      int   totalpages= jsonPath.getInt("total_pages");
      System.out.println(totalpages);
      Assert.assertEquals(totalpages, 2);
      System.out.println("assertion passed");
    }
}
