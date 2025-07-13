package IMPORTANTMISC;

import io.restassured.filter.session.SessionFilter;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class SessionFilterExample {

    public static void main(String[] args) {

        SessionFilter session = new SessionFilter();

        // Simulate login (token-based example)
        Response login = given()
                .baseUri("https://reqres.in")
                .contentType("application/json")
                .body("{\"email\": \"eve.holt@reqres.in\", \"password\": \"cityslicka\"}")
                .filter(session)
            .when()
                .post("/api/login")
            .then()
                .statusCode(200)
                .extract()
                .response();

        String token = login.jsonPath().getString("token");
        System.out.println("Session token: " + token);

        // Access another endpoint with same session
        given()
                .baseUri("https://reqres.in")
                .header("Authorization", "Bearer " + token)
                .filter(session)
            .when()
                .get("/api/users/2")
            .then()
                .statusCode(200);
    }
}
