package api.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import api.utilities.ExcelReader;

public class ExcelReader {
	
	static ConfigReader readconfig=new ConfigReader();
	public static FileInputStream fi;
	public FileOutputStream fo;
	public static XSSFWorkbook workbook;
	public static XSSFSheet sheet;
	public XSSFRow row;
	public static XSSFCell cell;
	public CellStyle style;   
	public File jsonFile;


	//data driven through feature file
	private static int getDataRow(String dataKey, int dataColumn) {
		int rowCount = sheet.getLastRowNum();
		for(int row=0; row<= rowCount; row++){
			if(ExcelReader.getCellData(row, dataColumn).equalsIgnoreCase(dataKey)){
				return row;
			}
		}
		return 0;		
	}

	private static String getCellData(int rowNumb, int colNumb) {
		cell = sheet.getRow(rowNumb).getCell(colNumb);

		if(cell.getCellType() == CellType.NUMERIC) {
			cell.setCellType(CellType.STRING);
		}
		String cellData = cell.getStringCellValue();
		return cellData;
	}

	public static Map<String, String> getData(String dataKey, String sheetName) throws Exception {

		Map<String, String> dataMap = new HashMap<String, String>();
		fi=new FileInputStream(readconfig.getXlpath());
		workbook=new XSSFWorkbook(fi);
		
		int noOfSheets = workbook.getNumberOfSheets();
		for(int sheetID = 0 ;sheetID < noOfSheets; sheetID++)
		{
			String name_of_sheet = workbook.getSheetName(sheetID);
			System.out.println(name_of_sheet);
		}
		sheet=workbook.getSheet(sheetName);

		int dataRow = getDataRow(dataKey.trim(), 0);

		if (dataRow == 0) {
			throw new Exception("NO DATA FOUND for dataKey: "+dataKey);
		}

		int columnCount = sheet.getRow(dataRow).getLastCellNum();

		for(int i=0;i<columnCount;i++) {
			cell = sheet.getRow(dataRow).getCell(i);
			String cellData = null; 
			if (cell != null) {
				if(cell.getCellType() == CellType.NUMERIC) {
					cell.setCellType(CellType.STRING);
				}
				cellData = cell.getStringCellValue();
			}
			dataMap.put(sheet.getRow(0).getCell(i).getStringCellValue(), cellData);
		}
		return dataMap;
	}


}
