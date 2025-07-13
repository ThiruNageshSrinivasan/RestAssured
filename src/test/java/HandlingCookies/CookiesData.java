package HandlingCookies;

import org.eclipse.sisu.launch.Main;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;
public class CookiesData {
	public static void main(String[] args) {
		RestAssured.baseURI="https://httpbin.org";
		
		String response=given().log().all().cookies(ProgramCookies.setcookie()).when().get("get").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println(response);
	}

}
