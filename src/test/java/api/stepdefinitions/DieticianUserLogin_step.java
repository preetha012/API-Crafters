package api.stepdefinitions;


import org.hamcrest.Matchers;
import api.RequestBody.UserRequestBody;
import api.endpoints.Routes;
import api.payload.UserPayload;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import api.utilities.LoggerLoad;


public class DieticianUserLogin_step {
	
	RequestSpecification request;
	Response response;
	ValidatableResponse valid_resp;
	public static UserPayload userpayload;
	public static String Dietician_token;
	
	
	
	@Given("User creates POST Request for login as Dietician.")
	public void user_creates_post_request_with_fields_and_from_excel() {
	    
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given().log().all()
	        		.header("Content-Type", "application/json");
	       LoggerLoad.logInfo("Request for Dietician Login Header Validated"+request.toString());
		  		
	}

	@When("User as dietician sends request Body with mandatory , additional fields with {string} and {string} from excel.")
	public void user_sends_request_body_with_mandatory_additional_fields(String KeyOption, String sheetname) throws Exception {
	   
		
			userpayload = UserRequestBody.PostUserBody(KeyOption,sheetname);
	    	 response = request.body(userpayload).post(Routes.login_Url);
	    	 
	    	LoggerLoad.logInfo("Dietician Login RequestBody passed"+response.getStatusCode());
	      
	      }	

	@Then("User as dietician receives Status for corresponding {string} with response body.")
	public void user_receives_status_with_response_body(String KeyOption) {
	    
			if(KeyOption.equalsIgnoreCase("DieticianLogin_InValid")) {
		
				valid_resp = response.then().log().all()
						 		.assertThat().statusCode(401)
						 		.body("token", Matchers.nullValue());
						 		
				 LoggerLoad.logInfo("Dietician Login for Invalid Request Validated");
				 
			}else if(KeyOption.equalsIgnoreCase("DieticianLogin_Valid")) {
			
				valid_resp = response.then().log().all()
					 		.assertThat().statusCode(200)
					 		.body("token", Matchers.notNullValue());
					
				Dietician_token = response.body().path("token");
				
				LoggerLoad.logInfo("Dietician Login for Valid Request Validated");
							
			}
	}
		
	
	
	
}
