package IMPORTANTMISC;

// Import static methods from RestAssured
import static io.restassured.RestAssured.*;

// Import RestAssured and Java IO classes
import io.restassured.RestAssured;
import java.io.File;

public class HandlingFileInput {

	public static void main(String[] args) {

		/*
		 * ----------------------------------------------------------
		 * Step 1: Specify the File to Upload
		 * ----------------------------------------------------------
		 * Use Java's File class to represent the file to upload.
		 * The file path must:
		 * - Be valid
		 * - Use double backslashes \\ or a single forward slash /
		 * - Be wrapped in double quotes ""
		 */
		File fileToUpload = new File("C:\\Users\\HP\\Desktop\\DashBoardMockAPI.txt");

		/*
		 * ----------------------------------------------------------
		 * Step 2: Set the Base URI
		 * ----------------------------------------------------------
		 * httpbin.org is a public API that echoes back the request.
		 * We'll use it to test file upload (via /post endpoint).
		 */
		RestAssured.baseURI = "https://httpbin.org";

		/*
		 * ----------------------------------------------------------
		 * Step 3: Send POST Request with Multipart File Upload
		 * ----------------------------------------------------------
		 * - .header("Content-Type", "multipart/form-data"): tells server we are uploading a file.
		 * - .multiPart("file", fileToUpload): attaches the file.
		 * - .log().all(): logs full request/response for debugging.
		 */
		String response = given()
				.log().all()
				.header("Content-Type", "multipart/form-data")
				.header("Connection", "keep-alive")
				.multiPart("file", fileToUpload) // Correct: File object used
			.when()
				.post("/post") // Echoes back what we send
			.then()
				.log().all()
				.extract()
				.response()
				.asString();

		/*
		 * ----------------------------------------------------------
		 * Step 4: Print the Response
		 * ----------------------------------------------------------
		 * This will print the server's echoed response.
		 * Useful for verifying file upload behavior.
		 */
		System.out.println("Final Response:\n" + response);
	}
}
