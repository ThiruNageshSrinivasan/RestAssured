package RequestResponseSpecification;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class RequestResponseCommpn {
public static RequestSpecification getReq() {
	 RequestSpecification req = new RequestSpecBuilder()
	            .setBaseUri("https://reqres.in")                // Base URI
	            .setRelaxedHTTPSValidation()                    // Accept self-signed certificates
	            .setContentType("application/json")             // Content-Type header
	            
	             .build();
	 return req;
}

public static ResponseSpecification getRes(int code) {
	  ResponseSpecification res1 = new ResponseSpecBuilder()
	            .expectStatusCode(code)                          // Expect HTTP 200 OK
	            .expectContentType("application/json")          // Expect content type JSON
	            .build();
	  return res1;
}
}
