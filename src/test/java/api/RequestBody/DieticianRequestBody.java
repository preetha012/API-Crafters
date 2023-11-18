package api.RequestBody;

import java.util.Map;

import api.payload.PatientPayload;
import api.payload.DieticianPayload;
import api.utilities.ExcelReader;

public class DieticianRequestBody {
	
	public static DieticianPayload  userpayload = new DieticianPayload();
	
	public static DieticianPayload PostUserBody(String KeyOption, String sheetname) throws Exception {
		
		Map<String, String> excelDataMap ;
	    excelDataMap = ExcelReader.getData(KeyOption, sheetname);	
	    System.out.println("Sheetname----"+sheetname);
	    System.out.print("password-"+excelDataMap.get("password"));
	    System.out.println("password---->>>>"+excelDataMap.get("password"));
	    userpayload.setPassword(excelDataMap.get("password"));
	    userpayload.setUserLoginEmail(excelDataMap.get("loginEmail"));
		
		return userpayload;
		
	}


}
