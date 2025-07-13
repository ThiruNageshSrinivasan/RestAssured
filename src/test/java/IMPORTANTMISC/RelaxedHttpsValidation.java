package IMPORTANTMISC;

// Import RestAssured core classes
import io.restassured.RestAssured;

// Import static methods to use RestAssured in fluent style
import static io.restassured.RestAssured.*;

public class RelaxedHttpsValidation {

    public static void main(String[] args) {

        /*
         * -------------------------------------------------------------
         * Step 1: Use .relaxedHTTPSValidation()
         * -------------------------------------------------------------
         * This tells RestAssured to:
         * - Ignore SSL certificate issues (e.g., self-signed, expired, untrusted)
         * - Useful for testing environments or non-production servers
         * 
         * We don't need to explicitly set baseURI here since we're using full URL in GET.
         */

        String response = given()
                .log().all()                        // Log entire request
                .relaxedHTTPSValidation()           // Bypass SSL verification
            .when()
                .get("https://self-signed.badssl.com") // URL with a self-signed certificate
            .then()
                .log().all()                        // Log full response
                .extract()
                .response()
                .asString();                        // Convert raw response to string

        /*
         * -------------------------------------------------------------
         * Step 2: Print Final Response
         * -------------------------------------------------------------
         * This helps verify that we successfully bypassed the SSL error
         * and got a valid HTML/HTTP response from the server.
         */
        System.out.println("✅ Final Response:\n" + response);
    }
}
