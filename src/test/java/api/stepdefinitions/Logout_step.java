package api.stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import org.hamcrest.Matchers;
import org.testng.Assert;
import api.endpoints.Routes;
import api.payload.UserPayload;

public class Logout_step {
	 
	  public RequestSpecification request;
	  public Response response;
	  ValidatableResponse valid_resp;
	  public int StatusCode;
	  public int ResponseCode;
	  String token = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyYWdhdmFyYXZpQGdtYWlsLmNvbSIsImlhdCI6MTcwMDQ1MjgzNywiZXhwIjoxNzAwNDgxNjM3fQ.5vHsx97ZE8KsUKsm5zs8-HPr7MPDmVDYL4vtMFKsFwwDuRorSz0N2KbLf9pOrtLBcwipY9LzAgkkHzGlCTsHqA";
	  
	  

@Given("Dietician user set Delete request for valid patientId url")
public void dietician_user_set_delete_request_for_valid_patient_id_url() {
	        RestAssured.baseURI = Routes.base_url;
            this.request = RestAssured
    		.given().log().all()
            .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token)
	       //.header( "Authorization", "Bearer " + token)
	        .pathParam("patientId", 3710);
	 		
}
@When("Dietician user send delete request for valid patientId")
public void dietician_user_send_delete_request_for_valid_patient_id() {
	        this.response = request.when().delete(Routes.DeletePatient_ByPatientId_Url);
	
}
@Then("the response will return delete status {int}")
public void the_response_will_return_delete_status(Integer int1) {
	valid_resp = response.then().log().all()
	 		.assertThat().statusCode(200);
	
}

   @Given("Dietician user set GET Request for logout as Dietician.")
	public void dietician_user_set_get_request_for_logout_as_dietician() {
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given().log().all()
	        		.header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	}

	@When("Dietician user send logout request")
	public void dietician_user_send_logout_request() {
		this.response = request.when().get(Routes.logout_Url);
		
	}

	@Then("the response will return status {int}")
	public void the_response_will_return_status(Integer int1) {
		valid_resp = response.then().log().all()
		 		.assertThat().statusCode(200);
		 
	}
}