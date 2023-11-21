package api.stepdefinitions;

import java.io.File;
import org.hamcrest.Matchers;
import api.utilities.ConfigReader;
import api.RequestBody.UserRequestBody;
import api.endpoints.Routes;
import api.payload.UserPayload;
import api.utilities.*;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class PatientRole_step {

	RequestSpecification request;
	Response response;
	
	ValidatableResponse PatientRole_valid_resp;
	
	public static UserRequestBody PatientRole_userRequestbody;
	public static UserPayload PatientRole_userpayload;
	public static String PatientRole_Patient_token;
	

	ConfigReader ConfigReader;

	// Constructor
	public PatientRole_step() {
		
		ConfigReader = new ConfigReader();
	}

	// Patient Login - valid

	@Given("User creates POST Request for login as Patient.")
	public void user_creates_post_request_with_fields_and_from_excel() {

		RestAssured.baseURI = Routes.base_url;
		this.request = RestAssured.given().log().all().header("Content-Type", "application/json");

	}

	@When("User as patient sends request Body with mandatory , additional fields with {string} and {string} from excel.")
	public void user_sends_request_body_with_mandatory_additional_fields(String KeyOption, String sheetname)
			throws Exception {

		PatientRole_userpayload = UserRequestBody.PostUserBody(KeyOption, sheetname);
		response = request.body(PatientRole_userpayload).post(Routes.login_Url);

	}

	@Then("User as Patient receives Status with response body.")
	public void user_as_patient_receives_status_with_response_body() {

		PatientRole_valid_resp = response.then().assertThat()
				.statusCode(200).and()
				.header("Content-Type", ("application/json")).and()
				.body(JsonSchemaValidator.matchesJsonSchema(new File(ConfigReader.getLoginJsonSchemaPath()))).and()
				.body("token", Matchers.notNullValue())
				.body("userLoginEmail", Matchers.notNullValue());

		PatientRole_Patient_token = response.body().path("token");
		//String Patient_userId = response.body().path("userId");
		
		LoggerLoad.logInfo("******** Patient Login ********\n");
		LoggerLoad.logInfo("Patient logged in successfully. Token :" + PatientRole_Patient_token);
		

	}

	// Patient Login - Invalid Password

	@When("User as patient sends request Body with invalid password {string} and {string} from excel.")
	public void user_as_patient_sends_request_body_with_invalid_password_and_from_excel(String InvalidPassword,
			String sheetname) throws Exception {

		PatientRole_userpayload = UserRequestBody.PostUserBody(InvalidPassword, sheetname);
		response = request.body(PatientRole_userpayload).post(Routes.login_Url);
	}

	@Then("User as Patient receives Status with response body invalid password.")
	public void user_as_patient_receives_status_with_response_body_invalid_password() {

		PatientRole_valid_resp = response.then().assertThat().statusCode(400).and().body("errorMessage",
				Matchers.equalToIgnoringCase("Invalid Password"));
		
	
		
	//	LoggerLoad.logInfo("******** Patient Login - Invalid Password  ******** \n"+PatientRole_valid_resp.log().all());

	}
	
	// Patient Login - Invalid email

	@When("User as patient sends request Body with invalid email {string} and {string} from excel.")
	public void user_as_patient_sends_request_body_with_invalid_email_and_from_excel(String InvalidEmail,
			String sheetname) throws Exception {

		PatientRole_userpayload = UserRequestBody.PostUserBody(InvalidEmail, sheetname);
		response = request.body(PatientRole_userpayload).post(Routes.login_Url);
	}

	@Then("User as Patient receives Status with response body invalid email.")
	public void user_as_patient_receives_status_with_response_body_invalid_email() {

		PatientRole_valid_resp = response.then().assertThat().statusCode(401).and().body("error",
				Matchers.equalToIgnoringCase("Unauthorized"));
       
	
		
		LoggerLoad.logInfo("******** Patient Login - Invalid email  ******** \n"+PatientRole_valid_resp.log().all());
		
	}

	// Get Report- By PatientID

	@Given("Patient user is logged inPatientRole")
	public void patient_user_is_logged_in_patient_role() {
		
		RestAssured.baseURI = Routes.base_url;
		this.request = RestAssured.given().log().all().header("Authorization", "Bearer " + PatientRole_Patient_token);
	}

	@When("User sends get request with valid patientID T04")
	public void user_sends_get_request_with_valid_patient_id_t04() {

		
		this.response = request.when().pathParam("patientId", ConfigReader.get_ReportPid())
				.get(Routes.GetPatient_ByPatientId_Url);
	}

	@Then("User can see report for PatientId PatientRole")
	public void user_can_see_report_for_patient_id_patient_role() {
		
		PatientRole_valid_resp = response.then().assertThat().statusCode(200);
		LoggerLoad.logInfo("******** Patient Login - Get Report- By PatientID  ******** \n"+PatientRole_valid_resp.log().body());
	}

	// Get Report- By fileId

	@When("User sends get request with valid fileID PatientRole")
	public void user_sends_get_request_with_valid_file_id_patient_role() {
		
		this.response = request.when().pathParam("fileId", ConfigReader.get_ReportFileId())
				.get(Routes.GetPatient_ByFieldId_Url);
	}

	@Then("User can see report for fileID PatientRole")
	public void user_can_see_report_for_file_id_patient_role() {
		
		PatientRole_valid_resp = response.then().assertThat().statusCode(200);
	}
	
	// Get report by invalid patientID
	@When("User sends get request with invalid patientID PatientRole")
	public void user_sends_get_request_with_invalid_patient_id_patient_role() {
		
		this.response = request.when().pathParam("patientId", ConfigReader.get_ReportInvalidPid())
				.get(Routes.GetPatient_ByPatientId_Url);
	}

	@Then("User can see error for invalid PatientId PatientRole")
	public void user_can_see_error_for_invalid_patient_id_patient_role() {
		PatientRole_valid_resp = response.then().assertThat().statusCode(404).and().body("errorCode",
				Matchers.equalToIgnoringCase("NOT_FOUND"));;
	}

	//Get Report by invalid file ID
	@When("User sends get request with invalid fileID PatientRole")
	public void user_sends_get_request_with_invalid_file_id_patient_role() {
		this.response = request.when().pathParam("fileId", ConfigReader.get_Report_InvalidFileId())
				.get(Routes.GetPatient_ByFieldId_Url);
	}

	@Then("User can see error for invalid fileId PatientRole")
	public void user_can_see_error_for_invalid_file_id_patient_role() {
		PatientRole_valid_resp = response.then().assertThat().statusCode(404).and().body("errorCode",
				Matchers.equalToIgnoringCase("NOT_FOUND"));
	}




}