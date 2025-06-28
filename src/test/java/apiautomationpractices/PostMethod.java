// Package declaration
package apiautomationpractices;

// Import static methods from RestAssured and Hamcrest for assertions
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import org.testng.Assert;

// Import necessary RestAssured classes
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import payloadData.PayLoad;

public class PostMethod {

    public static void main(String[] args) {

        /*
         * Step 1: Set the base URI
         * All relative endpoints will be appended to this
         */
        RestAssured.baseURI = "https://reqres.in";

        /*
         * Step 2: Send a POST request to create a new user
         * - Log full request details
         * - Add dummy and standard headers
         * - Send a raw JSON body with name & job
         * - Log full response
         * - Assert status code 201
         * - Assert that the returned 'name' matches expected value
         * - Extract the full response
         */
        Response res = 
            given()
                .log().all()
                .header("x-api-key", "reqres-free-v1") // Optional dummy header for practice
                .header("Content-Type", "application/json")
                .body(PayLoad.addEmployeeDetails("thiru","qa"))
            .when()
                .post("api/users")
            .then()
                .log().all()
                .assertThat().statusCode(201)            // Validate response status is 201 Created
                .body("name", equalTo("thiru"))          // Validate 'name' field in response
                .extract().response();                   // Extract entire response for further use

        /*
         * Step 3: Check and print response time
         */
        Long time = res.getTime();  // Get time in ms
        if (time < 5000) {
            System.out.println("✅ Testcase has been passed");
        } else {
            System.out.println("❌ Testcase has been failed");
        }

        /*
         * Step 4: Print entire raw response as string
         */
        System.out.println("Full Response:\n" + res.asString());

        /*
         * Step 5: Extract the 'createdAt' field using JsonPath
         * - Split the ISO datetime string by 'T' to extract only the date portion
         */
        JsonPath js = new JsonPath(res.asString());             // Parse JSON from response
        String createdDateTime = js.getString("createdAt");     // Get full datetime
        String[] dateOnly = createdDateTime.split("T");         // Split by 'T'
        System.out.println("Created Date: " + dateOnly[0]);     // Print only the date part
       Assert.assertEquals(dateOnly[0], java.time.LocalDate.now().toString());
       System.out.println("Expected: " + java.time.LocalDate.now());
       System.out.println("Actual:   " + dateOnly[0]);

    }
}
