package RequestResponseSpecification;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Example1 {

    public static void main(String[] args) {

        // ✅ Request Specification
        RequestSpecification req = new RequestSpecBuilder()
            .setBaseUri("https://reqres.in")
            .setContentType("application/json")
            .setRelaxedHTTPSValidation()
            .build();

        // ✅ Logging and Query Param
        RequestSpecification request = given()
            .log().all()
            .queryParam("page", 2)
            .spec(req);

        // ✅ Response Specification
        ResponseSpecification resSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .expectContentType("application/json")
            .build();

        // ✅ Send Request and Validate
        Response response = request
            .when().get("api/users")
            .then().spec(resSpec)
            .extract().response();

        // ✅ Print response body
        System.out.println("Response Body:\n" + response.asPrettyString());
    }
}
