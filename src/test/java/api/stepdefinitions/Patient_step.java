package api.stepdefinitions;

import java.io.File;
import java.util.Map;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import api.stepdefinitions.DieticianUserLogin_step;
import api.endpoints.Routes;
import api.payload.PatientPayload;
import api.RequestBody.PatientRequestBody;
import api.utilities.ExcelReader;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class Patient_step {
	
	
	RequestSpecification request;
	Response response;
	ValidatableResponse valid_resp;
	public static PatientRequestBody postRequestBody  = new PatientRequestBody();
	public static PatientPayload patientPayload;
	// DieticianUserLogin_step dietcnObj = new DieticianUserLogin_step();
	

	
	/*@Given("User as loged in as dietician and sends request Body with {string} and {string}.")
	public void user_as_dietician_sends_request_body_with_and(String password, String email) {
	    
		JSONObject jsonbody = new JSONObject();
   	  jsonbody.put("password", password);
   	  jsonbody.put("userLoginEmail", email); 
   	  RestAssured.baseURI = Routes.base_url;
   	  this.request = RestAssured
    		.given().log().all()
    		.header("Content-Type", "application/json");
    
    		response = request.body(jsonbody).post(Routes.login_Url);
    		response.then().assertThat().statusCode(200);
   	  
	} */
	
	@Given("Dietician creates POST Request to create a Patient")
	public void dietician_creates_post_request_to_create_a_patient() {
	    
		System.out.println("Token came ---->>>> "+DieticianUserLogin_step.Dietician_token);
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given().log().all()
	        .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
			//.header("Content-Type", "application/json");
	}

	@When("Dietician sends request Body with details with {string} and {string} from excel.")
	public void dietician_sends_request_body_with_details_with_and_from_excel(String KeyOption, String sheetname) throws Exception {
	   
		
		File f = new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf");
		patientPayload = postRequestBody.PostPatientBody(KeyOption,sheetname);
		System.out.println("User paylod--->>"+patientPayload);
	    response = request
	    		.body(patientPayload)
	    		//.body("./src/test/resources/testData/HyperThyroid_Report_final.pdf")
	    		//.multiPart("file", new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf"))
	    		//.multiPart("pdf", f, "application/pdf")
	    		.post(Routes.PostPatient_Url);
	    
		
	    
	}

	@Then("Dietician receives Patient Created Status with response body.")
	public void dietician_receives_patient_created_status_with_response_body() {
		
		System.out.println("Status Code --->"+response.getStatusCode());
		String jsonString = response.asString();
		System.out.println("Patient Details--->>>>>"+jsonString);
		valid_resp = response.then().log().all()
		 		.assertThat().statusCode(200);
		
	    
	}


	

}
