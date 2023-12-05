package api.stepdefinitions;

//import api.endpoints.MorbidityEndPoints;
import api.endpoints.Routes;
import io.cucumber.java.en.*;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.ObjectMapper;




public class Morbidity_step {
	ValidatableResponse valid_response;
	RequestSpecification request;
	Response response;
	
	
	@Given("User creates GET  Request for  All  all Morbidities")
	public void user_creates_get_request_for_all_all_morbidities() {
	    RestAssured.baseURI = Routes.base_url;
	    this.request = RestAssured.given().log().all()
	    .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	}
	@When("User sends HTTPS Request")
	public void user_sends_https_request() {
		this.response = request.when().get(Routes.GetAllMorbidity_Url);
	}
	@Then("User receives {int} OK Status with response body.")
	public void user_receives_ok_status_with_response_body(Integer int1) throws IOException {
		
		valid_response = response.then().log().all()
		 		.assertThat().statusCode(200).contentType(ContentType.JSON);
		 		
	 }
	
	
	@Given("User creates GET Request for the API endpoint with valid {string}")
	public void user_creates_get_request_for_the_api_endpoint_with_valid(String morbidityname) throws IOException {
	   
	   System.out.println("Morbityname   ***********---------->" + morbidityname);
	   
	   RestAssured.baseURI = Routes.base_url;
	   this.request = RestAssured.given().log().all()
			   .header("Authorization", "Bearer " +DieticianUserLogin_step.Dietician_token)
			   .pathParam("morbidityName", morbidityname );
			   
			  
	}
	
	@When("User sends HTTPS Reques")
	public void user_sends_https_reques() {
		this.response = request.when().get(Routes.GetMorbidity_ByName_Url);
	}
	
	@Then("User receives {int} OK Status with response  body.                                                                  Eg: [")
	public void user_receives_ok_status_with_response_body_eg(Integer int1) throws IOException {
		
		valid_response = response.then().log().all()
		 		.assertThat().statusCode(200).contentType(ContentType.JSON);
		 		
				
	}
	
	
	
	@Given("User creates GET Request for the API endpoint with Invalid {string}")
	public void user_creates_get_request_for_the_api_endpoint_with_invalid(String invalidMorbidityName) {
	   
		RestAssured.baseURI = Routes.base_url;
		   this.request = RestAssured.given().log().all()
				   .header("Authorization", "Bearer " +DieticianUserLogin_step.Dietician_token)
				   .pathParam("morbidityName", invalidMorbidityName );
	}
	
	@Then("User receives {int}  Status                                                                   Eg: [")
	public void user_receives_ok_status_eg(Integer int1) {
		valid_response = response.then().log().all()
		 		.assertThat().statusCode(404).contentType(ContentType.JSON);
	}
	
}