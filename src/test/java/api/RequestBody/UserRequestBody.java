
package api.RequestBody;

import java.util.Map;

import api.payload.UserPayload;
import api.utilities.ExcelReader;

public class UserRequestBody {
	
	public static UserPayload  userpayload = new UserPayload();
	
	public static UserPayload PostUserBody(String KeyOption, String sheetname) throws Exception {
		
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