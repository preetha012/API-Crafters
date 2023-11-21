package api.endpoints;

public class Routes {
	
	
	public static String base_url = "https://dietician-dev-41d9a344a720.herokuapp.com/dietician";
	
	
	//UserModule
	public static String login_Url = base_url+"/login";
	public static String logout_Url = base_url+"/logoutdietician";

	//MorbidityModule
	public static String GetAllMorbidity_Url = base_url+"/morbidity";
	public static String GetMorbidity_ByName_Url = base_url+"/morbidity/{morbidityName}";

	//PaientModule
	public static String PostPatient_Url = base_url+"/patient";
	public static String GetAllPatient_Url = base_url+"/patient";
	public static String UpdatePatient_ByPatientId_Url = base_url+"/patient/{patientId}";
	public static String GetPatient_ByPatientId_Url = base_url+"/patient/testReports/{patientId}";
	//public static String GetPatient_ByPatientId_Url = base_url+"/patient/testReports/";
	public static String GetPatient_ByFieldId_Url = base_url+"/patient/testReports/viewFile/{fileId}";
	public static String DeletePatient_ByPatientId_Url= base_url+"/patient/{patientId}";

}
//{{BaseUrl}}/patient/testReports/:validPID