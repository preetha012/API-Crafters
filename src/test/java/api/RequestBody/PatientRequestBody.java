package api.RequestBody;

import api.utilities.ExcelReader;

import java.util.Map;

import api.payload.PatientPayload;

public class PatientRequestBody extends ExcelReader{

	public static PatientPayload patientPayload  = new PatientPayload();
	
	public static PatientPayload PostPatientBody(String KeyOption, String sheetname) throws Exception {
		
		Map<String, String> excelDataMap;
	    excelDataMap = ExcelReader.getData(KeyOption, sheetname);	
		patientPayload.setFirstName(excelDataMap.get("FirstName"));
		patientPayload.setLastName(excelDataMap.get("LastName"));
		patientPayload.setContactNumber(1234567892);
		//patientPayload.setContactNumber(excelDataMap.get("ContactNumber"));
		patientPayload.setEmail(excelDataMap.get("Email"));
		patientPayload.setAllergy(excelDataMap.get("Allergy"));
		patientPayload.setFoodCategory(excelDataMap.get("FoodCategory"));
		//patientPayload.setDateOfBirth(excelDataMap.get("DateOfBirth"));
		patientPayload.setDateOfBirth("1988-03-04");
		
		return patientPayload;
		
	}
}
