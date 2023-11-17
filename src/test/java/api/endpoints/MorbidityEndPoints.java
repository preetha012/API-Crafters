package api.endpoints;


import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class MorbidityEndPoints {
	
	
	
	public Response GetAllMorbidity() {
		RequestSpecification request = RestAssured.given();
		Response response = request.given().when().get(Routes.GetAllMorbidity_Url);
		return response;
		
	}

}
