package api.stepdefinitions;

import java.io.File;
import java.util.*;
import static io.restassured.config.EncoderConfig.encoderConfig;
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
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DieticianRole {
	
	
	RequestSpecification request;
	Response response;
	ValidatableResponse valid_resp;
	public static PatientRequestBody postRequestBody  = new PatientRequestBody();
	public static PatientPayload patientPayload;
	// DieticianUserLogin_step dietcnObj = new DieticianUserLogin_step();
	

	/////***************** POST Patient *****************************
	@Given("Dietician creates POST Request to create a Patient")
	public void dietician_creates_post_request_to_create_a_patient() {
	    
		System.out.println("Token came ---->>>> "+DieticianUserLogin_step.Dietician_token);
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given()
	        		//.header("Content-Type", "application/json")
	        		.and()
	        		.contentType("multipart/form-data")
	        		//.header("content-type", "multipart/form-data")
	        	//	.multiPart("file", new File("C:/Users/anoop/eclipse-workspaceNew/API-Crafters/src/test/resources/testData/HyperThyroid_Report_final.pdf"),"application/pdf")
	        .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token)
	        .log().all();
	        
	        
	        
	        
	}

	@When("Dietician sends request Body with details with {string} and {string} from excel.")
	public void dietician_sends_request_body_with_details_with_and_from_excel(String KeyOption, String sheetname) throws Exception {
	   
		
	//	File f = new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf");
		patientPayload = postRequestBody.PostPatientBody(KeyOption,sheetname);
		
	  /*  response = request
	    		.body(patientPayload)
	    		
	    		//.body("./src/test/resources/testData/HyperThyroid_Report_final.pdf")
	    		//.multiPart("file", new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf"))
	    		//.multiPart("pdf", f, "application/pdf")
	    		.post(Routes.PostPatient_Url);
	    */
		/* String requestBody = "{\n"+
		        "  \"firstName\": \"Rama\",\n"+
		        "  \"lastName\": \"Vishal\",\n"+
		        "  \"contactNumber\": \"1234567892\",\n"+
		        "  \"email\": \"Rama@gmail.com\",\n"+
		        "  \"allergy\": \"Egg\",\n"+
		        "  \"foodCategory\": \"Non-Veg\",\n"+
		        "  \"dateOfBirth\": \"1988-12-04\"\n"+
		        "} \n";
		
	    response = request
	    		.body(requestBody)
	    		//.formParam("file", "file")
	    		//.queryParam("patientInfo","patientInfo")
	    		//.body("./src/test/resources/testData/HyperThyroid_Report_final.pdf")
	    		//.multiPart("file", new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf"))
	    		//.multiPart("pdf", f, "application/pdf")
	    		.post(Routes.PostPatient_Url);
	    */
	    
	   /* Map<String,String> keys = new HashMap<String, String>(){{
            put("firstName", "Rama");
            put("lastName", "Vishal");
            put("contactNumber", "1234567892");
            put("email", "Rama@gmail.com");
            put("allergy", "Egg");
            put("foodCategory", "Non-Veg");
            put("dateOfBirth", "1988-12-04");
           
	    }}; */
	   response =  request.when()
	    .config(RestAssured.config()
               .encoderConfig(encoderConfig().appendDefaultContentCharsetToContentTypeIfUndefined(false)))
	    .body(patientPayload)
	   // .body(keys)
		.post(Routes.PostPatient_Url);
		  
	}

	@Then("Dietician receives Patient Created Status with response body.")
	public void dietician_receives_patient_created_status_with_response_body() {
		
		//System.out.println("Status Code --->"+response.getStatusCode());
		
		valid_resp = response.then().log().all()
		 		.assertThat().statusCode(200);
		String jsonString = response.asString();
		System.out.println("Patient Details--->>>>>"+jsonString);
		
	    
	}

	
	 ///////************Get all**********************
	
	@Given("Dietician creates GET ALL Request to retrieve all Patients")
	public void dietician_creates_get_all_request_to_retrieve_all_patients() {
	    
		  RestAssured.baseURI = Routes.base_url;;
		  this.request = RestAssured.given().log().all()
				  .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	}

	@When("Dietician sends HTTPS request in Patient Module")
	public void dietician_sends_https_request_in_patient_module() {
		
		this.response = request.when().get(Routes.GetAllPatient_Url);
	}

	@Then("Dietician receives {int} OK Status with Patients data")
	public void dietician_receives_ok_status_with_patients_data(Integer int1) {
		
		valid_resp = response.then().log().all().assertThat().statusCode(200).contentType(ContentType.JSON);
		JsonPath jsonPathEvaluator = response.jsonPath();
		System.out.println("PatientID Response " + jsonPathEvaluator.get("patientID"));


		//String patientID = response.body().jsonPath().get("patientID");
	}

	
	//////******************** GET By PatientID**********************
	
	@Given("Dietician creates GET  Request with PatientId to retrieve the Patient details.")
	public void dietician_creates_get_request_with_patient_id_to_retrieve_the_patient_details() {
		
		RestAssured.baseURI = Routes.base_url;;
		  this.request = RestAssured.given().log().all()
				  .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	
	}

	@When("Dietician sends HTTPS request to get patient details in  Patient Module")
	public void dietician_sends_https_request_to_get_patient_details_in_patient_module() {
	    
		this.response = request.when()
				.pathParam("patientId", 2278)
				.get(Routes.GetPatient_ByPatientId_Url);
	}


	//////************ GET BY FileID Patient details***********************
	
	@Given("Dietician creates GET  Request with FileID to retrieve the Patient details.")
	public void dietician_creates_get_request_with_file_id_to_retrieve_the_patient_details() {
	    
		RestAssured.baseURI = Routes.base_url;;
		  this.request = RestAssured.given()
				  .contentType("application/pdf")
				  .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	
	}

	@When("Dietician sends HTTPS request with FileID to get patient details in  Patient Module")
	public void dietician_sends_https_request_with_file_id_to_get_patient_details_in_patient_module() {
	    
		this.response = request.when()
				.pathParam("fileId", "6558ea3ecb14874f354e658c")
				.get(Routes.GetPatient_ByFieldId_Url);
	}


	

}
