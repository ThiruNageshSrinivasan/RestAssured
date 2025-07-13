package Authentication;

// Importing static methods from RestAssured for cleaner syntax
import static io.restassured.RestAssured.given;
import io.restassured.RestAssured;

public class BearerToken {

    public static void main(String[] args) {

        /*
         * ---------------------------------------------------------------
         * 1. Access Token
         * ---------------------------------------------------------------
         * This is your Bearer Token issued by the GoREST API. 
         * It allows authorized access to protected endpoints (like creating a user).
         * 
         * NOTE: Always store sensitive tokens securely (not hardcoded in real projects).
         */
        String accesstoken = "bbccd57beffe174a6e72f5a81ef0a182ab05ba646f453aa0ae0b8217c03f4d79";

        /*
         * ---------------------------------------------------------------
         * 2. Set Base URI
         * ---------------------------------------------------------------
         * All REST Assured requests will use this as the root address.
         * We're interacting with GoREST API, so base URI is set accordingly.
         */
        RestAssured.baseURI = "https://gorest.co.in";

        /*
         * ---------------------------------------------------------------
         * 3. Create JSON Body for POST Request
         * ---------------------------------------------------------------
         * POST /public/v2/users requires a body with:
         * - name
         * - gender ("male" or "female")
         * - email (must be unique)
         * - status ("active" or "inactive")
         * 
         * We use System.currentTimeMillis() to generate a unique email every run.
         */
        String requestBody = "{\n"
                + "  \"name\": \"Thiru Test\",\n"
                + "  \"gender\": \"male\",\n"
                + "  \"email\": \"thiru" + System.currentTimeMillis() + "@example.com\",\n"
                + "  \"status\": \"active\"\n"
                + "}";

        /*
         * ---------------------------------------------------------------
         * 4. Send POST Request
         * ---------------------------------------------------------------
         * - given(): Prepare the request (headers, body, etc.)
         * - .log().all(): Log the full request details to the console (for debugging)
         * - .header(): Set headers one by one
         *     > Content-Type: tells server we're sending JSON data
         *     > Authorization: Bearer <token> for auth
         * - .body(): attach the request body (user data)
         * - when().post(): send POST request to /public/v2/users
         * - then().log().all(): log the full response
         * - extract().response().asString(): extract response as raw string
         */
        String response = given()
                .log().all()
                .header("Content-Type", "application/json") // Tells server to expect JSON
                .header("Authorization", "Bearer " + accesstoken) // Bearer token for auth
                .body(requestBody) // Attach request body
            .when()
                .post("/public/v2/users") // Send POST request
            .then()
                .log().all() // Log full response
                .extract()
                .response()
                .asString(); // Convert raw response to string

        /*
         * ---------------------------------------------------------------
         * 5. Print the Final Response
         * ---------------------------------------------------------------
         * This will show the API's response JSON (success or failure)
         */
        System.out.println(response);
    }
}
