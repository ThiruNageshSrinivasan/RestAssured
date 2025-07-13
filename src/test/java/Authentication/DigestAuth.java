package Authentication;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;

public class DigestAuth {

	public static void main(String[] args) {
		RestAssured.baseURI="https://httpbin.org/digest-auth";
		String response=given().log().all().auth().digest("thiru","thiru")
		.when().get("undefined/thiru/thiru").then().log().all().extract().response().asString();
		
		System.out.println(response);

	}

}
