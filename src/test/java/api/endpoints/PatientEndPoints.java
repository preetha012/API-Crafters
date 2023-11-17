package api.endpoints;


import api.payload.PatientPayload;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class PatientEndPoints {
	
	public Response CreatePatient(PatientPayload payload)
	{
		RequestSpecification request = RestAssured.given()
										.header("Content-Type", "application/json");
		Response response = request
							.body(payload)
							.when()
							.post(Routes.PostPatient_Url);
		return response;
	}

}
