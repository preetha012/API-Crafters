package api.RequestBody;

import api.utilities.ExcelReader;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

import api.payload.PatientPayload;

public class PatientRequestBody extends ExcelReader{

	public static PatientPayload patientPayload  = new PatientPayload();
	
	public static PatientPayload PostPatientBody(String KeyOption, String sheetname) throws Exception {
		
		Map<String, String> excelDataMap = null;
	    excelDataMap = ExcelReader.getData(KeyOption, sheetname);	
		patientPayload.setFirstName(excelDataMap.get("FirstName"));
		patientPayload.setLastName(excelDataMap.get("LastName"));
		patientPayload.setContactNumber(excelDataMap.get("ContactNumber"));
		patientPayload.setEmail(excelDataMap.get("Email"));
		patientPayload.setAllergy(excelDataMap.get("Allergy"));
		patientPayload.setFoodCategory(excelDataMap.get("FoodCategory"));
		patientPayload.setDateOfBirth(excelDataMap.get("DateOfBirth"));
		System.out.println("ContactNo from excel--->\n"+excelDataMap.get("ContactNumber"));
		System.out.println("ContactNo from excel--->\n"+excelDataMap.get("DateOfBirth"));

		
		
		
		 return patientPayload;
		
	}
}
