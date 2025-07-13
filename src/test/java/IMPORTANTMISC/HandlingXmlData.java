package IMPORTANTMISC;

// Import RestAssured library for API automation
import io.restassured.RestAssured;
// Import payload provider class from another package
import payloadData.PayLoad;

// Import static methods from RestAssured for fluent API syntax
import static io.restassured.RestAssured.*;

public class HandlingXmlData {

    public static void main(String[] args) {
        
        // ---------------------------------------------------------------------------------
        // Step 1: Set the base URI of the API
        // ---------------------------------------------------------------------------------
        // This is the root address of the API you're going to test.
        // All endpoint paths will be appended to this.
        RestAssured.baseURI = "https://petstore.swagger.io";

        // ---------------------------------------------------------------------------------
        // Step 2: Prepare and send the POST request using RestAssured
        // ---------------------------------------------------------------------------------
        // given()         : Prepares the request by adding headers, body, params, etc.
        // log().all()     : Logs the entire request for debugging purposes.
        // header()        : Sets the Content-Type as "application/xml". (⚠️ Not supported by Petstore)
        // body()          : Adds the request payload (XML format) from PayLoad.xmlpayload() method.
        //
        // when()          : Triggers the actual HTTP method (in this case, POST).
        // post("v2/pet")  : Sends the POST request to the "v2/pet" endpoint (relative to baseURI).
        //
        // then()          : Start of response validation/assertion block.
        // log().all()     : Logs the full response from the server.
        // assertThat()    : Starts the assertion chain.
        // statusCode(200) : Verifies that the response returned HTTP 200 OK.
        // extract()       : Extracts the response for further use.
        // asString()      : Converts the response body into a plain String format.
        // ---------------------------------------------------------------------------------

        String response = 
            given()
                .log().all()
                .header("Content-Type", "application/xml") // ⚠️ Swagger Petstore expects JSON, not XML
                .body(PayLoad.xmlpayload())                // XML payload defined in external class
            .when()
                .log().all()
                .post("v2/pet")
            .then()
                .log().all()
                .assertThat()
                .statusCode(200)                            // ❌ This will fail with 415 due to media type mismatch
                .extract()
                .response()
                .asString();

        // ---------------------------------------------------------------------------------
        // Step 3: Print the final response to console
        // ---------------------------------------------------------------------------------
        System.out.println(response);
    }

}
