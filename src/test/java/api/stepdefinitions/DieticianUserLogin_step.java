package api.stepdefinitions;

import java.util.Map;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import api.RequestBody.UserRequestBody;
import api.endpoints.Routes;
import api.payload.UserPayload;
import api.utilities.ExcelReader;
import api.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;


public class DieticianUserLogin_step {
	
	RequestSpecification request;
	Response response;
	ValidatableResponse valid_resp;
	public static UserRequestBody userRequestbody;
	public static UserPayload userpayload;
	public static String Dietician_token;
	
	@Given("User creates POST Request for login as Dietician.")
	public void user_creates_post_request_with_fields_and_from_excel() {
	    
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given().log().all()
	        		.header("Content-Type", "application/json");
		  		
	}

	@When("User as dietician sends request Body with mandatory , additional fields with {string} and {string} from excel.")
	public void user_sends_request_body_with_mandatory_additional_fields(String KeyOption, String sheetname) throws Exception {
	   
		
			userpayload = userRequestbody.PostUserBody(KeyOption,sheetname);
		
	    	  System.out.println("------"+Routes.login_Url);
	    	  response = request.body(userpayload).post(Routes.login_Url);
	      
	      }	

	@Then("User as dietician receives Status with response body.")
	public void user_receives_status_with_response_body_and_from_excel() {
	    
		 valid_resp = response.then().log().all()
				 		.assertThat().statusCode(200)
				 		.body("token", Matchers.notNullValue());
		// Dietician_token = response.body().asString();
		 Dietician_token = response.body().path("token");
		 System.out.println("Token ----->>>"+Dietician_token);
				 		
	}

/*	public  String getToken() {
		Dietician_token = response.body().path("token");
		 System.out.println("Token ----->>>"+Dietician_token);
		 return Dietician_token;
	} */
	
	
	
}
