package apiautomationpractices;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;

public class HandlingStaticPayload {

    public static void main(String[] args) throws IOException {

        /*
         * Step 1: Set the base URI for the API.
         * All further requests will use this as the root.
         * Example: https://reqres.in/api/users
         */
        RestAssured.baseURI = "https://reqres.in";

        /*
         * Step 2: Read the JSON file as a String.
         * The file must contain a valid JSON payload.
         * We use Files.readAllBytes to read the entire content at once.
         * 
         * Replace the path below with your actual file location.
         */
        String payload = new String(Files.readAllBytes(Paths.get("C:\\ProjectFinal\\com.restassured\\src\\test\\resources\\DashBoardMockAPI.txt")));

        /*
         * Step 3: Send the POST request to the API endpoint using Rest Assured.
         * 
         * - .log().all() logs the full request and response details
         * - .header() sets headers: Content-Type and optional API key
         * - .body(payload) sets the JSON payload we read from file
         * - .post("api/users") hits the /api/users POST endpoint
         * - .then().assertThat().statusCode(201) validates success
         * - .extract().asString() extracts the full response as a string
         */
        String response = given()
                .log().all()
                .header("x-api-key", "reqres-free-v1") // optional header for some APIs
                .header("Content-Type", "application/json") // tells server we're sending JSON
                .body(payload) // attach the body content
            .when()
                .post("api/users") // send POST request to this path
            .then()
                .log().all() // log full response
                .assertThat().statusCode(201) // validate status code
                .extract().response().asString(); // store the response as string

        /*
         * Step 4: Print the response returned by the server.
         * This helps verify the result manually or for debugging.
         */
        System.out.println("Response:\n" + response);
    }
}
