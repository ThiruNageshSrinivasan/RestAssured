// Package declaration
package apiautomationpractices;

// Importing RestAssured static method 'given' for better readability
import static io.restassured.RestAssured.given;

import java.util.LinkedHashMap;
import java.util.Map;

import org.testng.Assert; // For assertion

// Importing necessary classes from RestAssured
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class UsingMapToPassData {

	public static void main(String[] args) {

		/*
		 * Step 1: Create a Map to represent the JSON payload - Using LinkedHashMap to
		 * preserve insertion order (not required, but good practice)
		 */
		Map<String, Object> mp = new LinkedHashMap<String, Object>();

		/*
		 * Step 2: Generate dynamic values for isbn and aisle - isbn is a string with
		 * current timestamp - aisle is a random number between 0 and 999
		 */
		String u_isbn = "user_" + System.currentTimeMillis();
		int u_asile = (int) (Math.random() * 1000); // Typecast double to int

		/*
		 * Step 3: Populate the map with key-value pairs for the request body These keys
		 * match the expected JSON structure by the API
		 */
		mp.put("name", "Srisusmitha");
		mp.put("isbn", u_isbn);
		mp.put("aisle", u_asile);
		mp.put("author", "thiruqa");

		/*
		 * Step 4: Set the base URI of the API
		 */
		RestAssured.baseURI = "http://216.10.245.166";

		/*
		 * Step 5: Send POST request to /Library/Addbook.php - Log the request and
		 * response - Add header content-type as application/json - Pass the map as body
		 * (converted to JSON automatically by RestAssured) - Validate status code 200 -
		 * Extract response as a string
		 */
		String response = given().log().all().headers("content-Type", "application/json").body(mp).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		/*
		 * Step 6: Print the raw response for debugging
		 */
		System.out.println("The Response is: " + response);

		/*
		 * Step 7: Build the expected result The API returns an ID field which is a
		 * concatenation of isbn and aisle So we prepare our expected result accordingly
		 */
		String expected_result = u_isbn + u_asile;
		System.out.println("Expected ID: " + expected_result);

		/*
		 * Step 8: Parse the response using JsonPath - Extract the actual ID value
		 * returned in the response
		 */
		JsonPath js = new JsonPath(response);
		String actual_result = js.getString("ID");
		System.out.println("Actual ID: " + actual_result);

		/*
		 * Step 9: Compare actual vs expected using TestNG Assert If they match, test
		 * passes. Otherwise, it fails.
		 */
		Assert.assertEquals(actual_result, expected_result);
		System.out.println("Test case passed with the assertion comparison");
	}
}
