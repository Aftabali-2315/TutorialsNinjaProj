package com.tutorialsNinja.utils;

import java.io.File;
import java.io.FileInputStream;
import java.util.Date;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Utilities {

	public static final int implicaitWait = 10;
	public static final int pageLoadTimeOut = 5;
	
	public String generateTimeStamp() {
		Date date = new Date();
		String timestamp = date.toString().replace(" ", "_").replace(":", "_");
		return "aftabalimulla"+timestamp+"@gmail.com";
	}
	
	public static Object[][] getExcelData(String sheetname) {
		File excelfile = new File(System.getProperty("user.dir")+"\\src\\main\\java\\com\\tutorialsNinja\\testData\\TutorialsNinjaTestData.xlsx");
		XSSFWorkbook workbook = null;
		try {
			FileInputStream fis = new FileInputStream(excelfile);
			workbook = new XSSFWorkbook(fis);
		}catch(Throwable e) {
			e.printStackTrace();
		}
		XSSFSheet sheet = workbook.getSheet(sheetname);
		int rows = sheet.getLastRowNum();
		int cols = sheet.getRow(0).getLastCellNum();
		
		Object data[][] = new Object[rows][cols];
		
		for(int i=0;i<rows;i++) {
			XSSFRow row = sheet.getRow(i+1);
			for(int j=0;j<cols;j++) {
				XSSFCell cell = row.getCell(j);
				CellType celltype = cell.getCellType();
				
				switch(celltype) {
				case STRING:
					data[i][j] = cell.getStringCellValue();
					break;
				case NUMERIC:
					data[i][j] = Integer.toString((int)cell.getNumericCellValue());
					break;
				case BOOLEAN:
					data[i][j] = cell.getBooleanCellValue();
					break;
 				}
			}
		}
		return data;
	}
}
