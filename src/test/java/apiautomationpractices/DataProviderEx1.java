package apiautomationpractices;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import payloadData.PayLoad;



	public class DataProviderEx1 {

	    /*
	     * This list stores all API responses.
	     * After all tests are run, it will be used to print all results together.
	     */
	    List<String> allResponses = new ArrayList<>();

	    /*
	     * This is a TestNG DataProvider.
	     * It provides three different sets of book data (isbn, aisle, author)
	     * to the test method below.
	     */
	    @DataProvider(name = "BookDetails")
	    public Object[][] getBookData() {
	        return new Object[][] {
	            { "NFGG", "1234", "THIRU" },
	            { "AABB", "2345", "TITJ" },
	            { "AAYB", "2745", "TOTJ" }
	        };
	    }

	    /*
	     * This test method runs once for each set of data from the DataProvider.
	     * It:
	     * - Builds the payload using the Payload class
	     * - Sends a POST request to /Library/Addbook.php
	     * - Logs the request and response
	     * - Asserts that status code is 200
	     * - Saves the response into a list for printing later
	     */
	    @Test(dataProvider = "BookDetails")
	    public void addBook(String isbn, String aisle, String author) {
	        // Set the base URI for Rest Assured
	        RestAssured.baseURI = "http://216.10.245.166";

	        // Generate the JSON payload
	        String payload = PayLoad.getBookDetails(isbn, aisle, author);

	        // Send the POST request and get the response
	        String response = given()
	                .log().all() // Log request details
	                .header("Content-Type", "application/json") // Set header
	                .body(payload) // Set body
	            .when()
	                .post("/Library/Addbook.php") // API endpoint
	            .then()
	                .log().all() // Log response details
	                .assertThat().statusCode(200) // Assert HTTP 200 OK
	                .extract().response().asString(); // Extract response as string

	        // Store the response for summary printing
	        allResponses.add("ISBN: " + isbn + " → Response: " + response);
	    }

	    /*
	     * After all test methods run, this method executes automatically.
	     * It prints all responses collected from the above tests.
	     */
	    @AfterClass
	    public void printAllResponses() {
	        System.out.println("\n================== All API Responses ==================\n");
	        for (String res : allResponses) {
	            System.out.println(res); // Print each response stored in the list
	        }
	        System.out.println("\n=======================================================\n");
	    }
	}
