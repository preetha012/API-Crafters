package api.utilities;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

	
	 Properties pro;

	public ConfigReader() {
		File src = new File("./src/test/resources/configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is " + e.getMessage());
		}
	}
	
	//get XLPath from config
	public String getXlpath() {
		String xlPath = pro.getProperty("excel_path");
		return xlPath;
	}
	
	public String getLoginJsonSchemaPath() {
		String jsPath = pro.getProperty("login_jsonSchema");
		return jsPath;
	}
	
	public int get_ReportPid() {
		String getReportPid = pro.getProperty("getReportPid");
		
		return Integer.parseInt(getReportPid);
	
	}
	
	public int get_ReportInvalidPid() {
		String getReportInvalidPid = pro.getProperty("getReportInvalidPid");
		
		return Integer.parseInt(getReportInvalidPid);
	
	}
	
	public String get_ReportFileId() {
		String getReportFileId = pro.getProperty("getReportFileId");
		
		return getReportFileId; 
	}
		
		public String get_Report_InvalidFileId() {
			
			String getReportInvalidFileId = pro.getProperty("getReportInvalidFileId");
			
			return getReportInvalidFileId;
	}
}