package RequestResponseSpecification;

import static io.restassured.RestAssured.given;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Example2 {

    public static void main(String[] args) {

        // 🔹 Step 1: Use reusable request spec from utility class and add query param
        RequestSpecification res = given()
            .log().all()
            .queryParam("page", 2)
            .spec(RequestResponseCommpn.getReq()); // use request spec from utility

        // 🔹 Step 2: Send GET request and validate with reusable response spec
        Response response = res
            .when().get("api/users") // actual GET call
            .then()
            .spec(RequestResponseCommpn.getRes(200)) // expected status code
            .extract()
            .response();

        // 🔹 Step 3: Print formatted response
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}


