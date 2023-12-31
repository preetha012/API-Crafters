package api.stepdefinitions;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.*;
import org.hamcrest.Matchers;
import org.json.simple.JSONObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import api.utilities.ConfigReader;
import api.stepdefinitions.DieticianUserLogin_step;
import api.endpoints.Routes;
import api.payload.UserPayload;
import api.payload.PatientPayload;
import api.RequestBody.UserRequestBody;
import api.RequestBody.PatientRequestBody;
import api.utilities.ExcelReader;
import api.utilities.LoggerLoad;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class DieticianRole {
	
	
	RequestSpecification request;
	Response response;
	ValidatableResponse valid_resp;
	public static ConfigReader readconfig;
	public static UserPayload userpayload;
	public static PatientRequestBody postRequestBody ;
	public static PatientPayload patientPayload;
	public static int PatientId_static;
	public static String FileId_static;
	 File file = new File("./src/test/resources/testData/HyperThyroid_Report_final.pdf");
	 
	

	/////***************** POST Patient *****************************
	@Given("Dietician creates POST Request to create a Patient")
	public void dietician_creates_post_request_to_create_a_patient() {
	    
		try {
		
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        				.given().multiPart("file",file,"multipart/form-data")
	        				.header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token)      		
	        				.log().all();	    
	     LoggerLoad.logInfo("Create Patient Request Header validated with Bearer Token"+DieticianUserLogin_step.Dietician_token);
		
		}catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
		} 
	}

	@When("Dietician sends request Body with details with {string} and {string} from excel.")
	public void dietician_sends_request_body_with_details_with_and_from_excel(String KeyOption, String sheetname) throws Exception {
	   
		try {
				patientPayload = PatientRequestBody.PostPatientBody(KeyOption,sheetname);
				ObjectMapper mapper = new ObjectMapper();
			    String json = mapper.writeValueAsString(patientPayload);  
				this.response =  request.when().formParam("patientInfo",json)
						   			.post(Routes.PostPatient_Url);
				System.out.println("Post Patient Valid Body-->>>"+response.asString());
				LoggerLoad.logInfo("Create Patient Request with  Request Body send");
		
		}catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
		}	   		   
		  
	}

	@Then("Dietician receives for {string} Patient Created Status with response body.")
	public void dietician_receives_patient_created_status_with_response_body(String KeyOption) {
		
		try {
		
			if(KeyOption.equals("postPatient_MissingName")
				|| KeyOption.equals("postPatient_MissingEmail") 
				|| KeyOption.equals("postPatient_MissingContact")
				|| KeyOption.equals("postPatient_MissingDOB")
				|| KeyOption.equals("postPatient_Invalid_FoodCat")
				|| KeyOption.equals("postPatient_Invalid_Allergy")
				|| KeyOption.equals("postPatient_duplicate_UniqueValue")){
				
				valid_resp = response.then().log().all().assertThat().statusCode(400);
				//String jsonString = valid_resp.toString();
				//assertEquals(jsonString.contains("INVALID_REQ_DATA"), true);			
								
				LoggerLoad.logInfo("INVALID_REQ_DATA Error Displayed");
				
				
			}else if (KeyOption.equals("postPatient_Valid")){
				
				valid_resp = response.then().log().all()
				 	.assertThat().statusCode(201);
				 	//.and().body(JsonSchemaValidator.matchesJsonSchema(new File("./src/test/resources/201_PostPatientSucessfulSchema.json")));
				
				PatientId_static = response.body().path("patientId");
				String jsonString = response.asString();
				System.out.println("Patient Details--->>>>>"+jsonString);
				System.out.println("Patient id created --->>>>>"+PatientId_static);
			
			}
		}catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
		}
		    
	}

	
	 ///////************Get all**********************
	
	@Given("Dietician creates GET ALL Request to retrieve all Patients")
	public void dietician_creates_get_all_request_to_retrieve_all_patients() {
	    try {
	    	
		  RestAssured.baseURI = Routes.base_url;;
		  this.request = RestAssured.given().log().all()
				  .header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
		 
		  LoggerLoad.logInfo("GET ALL Patient Request Header with Bearer Token Validated");
	    
	    }catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
	}

	@When("Dietician sends HTTPS request in Patient Module")
	public void dietician_sends_https_request_in_patient_module() {
		try {
			
			this.response = request.when().get(Routes.GetAllPatient_Url);
			
			LoggerLoad.logInfo("GET ALL Patients Request send!");
		
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
	}

	@Then("Dietician receives {int} OK Status with Patients data")
	public void dietician_receives_ok_status_with_patients_data(Integer int1) {
		
		try {
				
			valid_resp = response.then().log().all().assertThat().statusCode(200)
						.contentType(ContentType.JSON);
			JsonPath jsonPathEvaluator = response.jsonPath();
			
			LoggerLoad.logInfo("GET ALL Patients Response Validated"+response.getStatusLine());
		
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }

	}

	///*************************** UPDATED PatientId *******************************
	
	@Given("Dietician creates PUT Request to Update a Patient")
	public void dietician_creates_put_request_to_update_a_patient() {
		
		System.out.println("Token came ---->>>> "+DieticianUserLogin_step.Dietician_token);
		 RestAssured.baseURI = Routes.base_url;
	        this.request = RestAssured
	        		.given()
	        		//.multiPart("file",file,"multipart/form-data")
	        		.header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token)      		
	        		.log().all();
		
	    
	}

	@When("Dietician sends PUT request to update with details with {string} and {string}.")
	public void dietician_sends_put_request_to_update_with_details_with_and(String KeyOption, String sheetname) throws Exception {
	   
		try {
			if(KeyOption.equalsIgnoreCase("updatePatient_Valid")) {
			
				System.out.println("Patientid to update--->>"+PatientId_static);
				patientPayload = PatientRequestBody.PostPatientBody(KeyOption,sheetname);
				ObjectMapper mapper = new ObjectMapper();
			    String json = mapper.writeValueAsString(patientPayload);
				this.response =  request.when().formParam("patientInfo",json)
						 			.pathParam("patientId", PatientId_static)
						 			.put(Routes.UpdatePatient_ByPatientId_Url);
				
			} else if(KeyOption.equalsIgnoreCase("updatePatient_InValid")) {
				
				patientPayload = PatientRequestBody.PostPatientBody(KeyOption,sheetname);
				ObjectMapper mapper = new ObjectMapper();
			    String json = mapper.writeValueAsString(patientPayload);
				this.response =  request.when().formParam("patientInfo",json)
						 			.pathParam("patientId", 0010)
						 			.put(Routes.UpdatePatient_ByPatientId_Url);
				
			}
			
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
		
	}

	@Then("Dietician receives for {string} Patient Updated Status with response body.")
	public void dietician_receives_for_patient_updated_status_with_response_body(String KeyOption) {
	    
		try {
			if(KeyOption.equalsIgnoreCase("updatePatient_Valid")) {
				
				valid_resp = response.then().log().all()
				 			.assertThat().statusCode(200);
				String jsonString = response.asString();
				System.out.println("Patient Details--->>>>>"+jsonString);
				
				LoggerLoad.logInfo("Updated Patient Details for "+ PatientId_static );
				
			} else if(KeyOption.equalsIgnoreCase("updatePatient_InValid")) {
				
				valid_resp = response.then().log().all()
				 			.assertThat().statusCode(404);
				 			
				String jsonString = response.asString();
				
				LoggerLoad.logInfo("NOT FOUND Message for inavalid PatienID as End point"+ jsonString);
			}
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
		
	}
	
	
	//////******************** GET By PatientID**********************
	
	@Given("Dietician creates GET  Request with PatientId to retrieve the Patient details.")
	public void dietician_creates_get_request_with_patient_id_to_retrieve_the_patient_details() {
		try {
			
			RestAssured.baseURI = Routes.base_url;;
			this.request = RestAssured.given().log().all()
							.header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
		 
			LoggerLoad.logInfo("GET Single Patient Request Header with Bearer Token Validated");
		
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
	}

	@When("Dietician sends HTTPS request with {string} to get patient details in  Patient Module")
	public void dietician_sends_https_request_to_get_patient_details_in_patient_module(String KeyOption) {
	    
		try {
			
			if (KeyOption.equals("PatientID_Valid")) {
				System.out.println("Patient Id to get--->>>"+PatientId_static);
				this.response = request.when()
						.pathParam("patientId", PatientId_static)
						.get(Routes.GetPatient_ByPatientId_Url);
				
			}else if(KeyOption.equals("PatientID_InValid")) {
				
				this.response = request.when()
						.pathParam("patientId", 3456)
						.get(Routes.GetPatient_ByPatientId_Url);
				LoggerLoad.logInfo("Dietician should view only his Patients, he is able to see other Patient Details now !");
			}	
			LoggerLoad.logInfo("GET Single Patient Request send with endpoint");
			
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
		
	}

	@Then("Dietician receives Status for corresponding {string} Patients data")
	public void dietician_receives_status_for_corresponding_patients_data(String KeyOption) {
	   
		try {
			
			if(KeyOption.equalsIgnoreCase("PatientID_Valid")) {
				
				System.out.println("Reached get by id ----->>>");
				valid_resp = response.then().log().all().assertThat().statusCode(200)
						.contentType(ContentType.JSON);
				FileId_static= response.body().path("fileId[0]");
				System.out.println("FieldId created--->>>>>>"+FileId_static);
				LoggerLoad.logInfo("Single Patient Response Validated for valid ID");
				
			}else if(KeyOption.equalsIgnoreCase("PatientID_InValid")) {
				
				valid_resp = response.then().log().all().assertThat().statusCode(404)
									.contentType(ContentType.JSON);
				
				LoggerLoad.logInfo("Single Patient Response Validated for InValid ID");
			}
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
	}
	
	

	//////************ GET BY FileID Patient details***********************
	
	@Given("Dietician creates GET  Request with FileID to retrieve the Patient details.")
	public void dietician_creates_get_request_with_file_id_to_retrieve_the_patient_details() {
	    try {
	    	
	    	RestAssured.baseURI = Routes.base_url;;
	    	this.request = RestAssured.given()
	    			.contentType("application/pdf")
	    			.header("Authorization", "Bearer " + DieticianUserLogin_step.Dietician_token);
	
	    	LoggerLoad.logInfo("GET FileID Request given!");
	    } catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
			
	}

	@When("Dietician sends HTTPS request with {string} as FileID for patient.")
	public void dietician_sends_https_request_with_file_id_to_get_patient_details_in_patient_module(String KeyOption) {
		try {
			
			if(KeyOption.equalsIgnoreCase("FieldID_Valid")) {
				System.out.println("Valid FileId-->>>>"+FileId_static);
				this.response = request.when()
						.pathParam("fileId", FileId_static)
						.get(Routes.GetPatient_ByFieldId_Url);
				
				LoggerLoad.logInfo("GET FileID for Valid ID Request send!");
			}else if(KeyOption.equalsIgnoreCase("FieldID_InValid")) {
				this.response = request.when()
						.pathParam("fileId", "abcd1")
						.get(Routes.GetPatient_ByFieldId_Url);
				
				LoggerLoad.logInfo("GET FileID for In_Valid ID Request send!");
				
			}
		} catch (Exception ex) {
		      LoggerLoad.logInfo(ex.getMessage());
		      ex.printStackTrace();
	    }
		
	}

	@Then("Dietician receives Status for corresponding {string} FieldID Patients data.")
	public void dietician_receives_status_for_corresponding_field_id_patients_data(String KeyOption) {
	  try {
		  
			if(KeyOption.equalsIgnoreCase("FieldID_Valid")) {
				
				valid_resp = response.then().assertThat().statusCode(200);
				System.out.println("StatusCode--->"+response.getStatusCode());
				
				LoggerLoad.logInfo("Response for Valid FileID Validated");
	
			
			}else if(KeyOption.equalsIgnoreCase("FieldID_InValid")) {
				
				valid_resp = response.then().assertThat().statusCode(404);
				System.out.println("StatusCode--->"+response.getStatusCode());					
				
				LoggerLoad.logInfo("Response for InValid FileID Validated");
			}
	  } catch (Exception ex) {
	      LoggerLoad.logInfo(ex.getMessage());
	      ex.printStackTrace();
    }
		
		
	}
	

}
