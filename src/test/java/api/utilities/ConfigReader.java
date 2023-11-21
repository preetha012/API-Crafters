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
	
	//get PDf Report Path
	public String getPdfReportpath() {
		String pdfReportPath = pro.getProperty("Pdf_Report1");
		return pdfReportPath;
	}
	
	// get 201 Patient Create sucess Schema
	public String postSucessSchema() {
		String post201Schema = pro.getProperty("Schema_postSucess");
		return post201Schema;
	}
}