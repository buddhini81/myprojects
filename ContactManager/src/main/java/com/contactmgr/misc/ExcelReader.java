package com.contactmgr.misc;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {
	
	 public static final String SAMPLE_XLSX_FILE_PATH = "upload-dir/eSolve All Contacts - Added to Gmail Bulk.xlsx";
	 
	 public static List<String> readExcelContacts() {
		 Workbook workbook = null;
		 List<String> contacts = new ArrayList<String>();
		 try {
	        workbook = WorkbookFactory.create(new File(SAMPLE_XLSX_FILE_PATH));
	        Sheet sheet = workbook.getSheetAt(0);
	        DataFormatter dataFormatter = new DataFormatter();
	        sheet.forEach(row -> {
		        if(row.getRowNum() != 0)  {
		            row.forEach(cell -> {
		            	String cellValue = dataFormatter.formatCellValue(cell);
		            	contacts.add(cellValue);
		            });
		        }
	        });

		 } catch(Exception e) {
			 e.printStackTrace();
		 } finally {
			 try {
				 workbook.close();
			 } catch(Exception e) {
				 e.printStackTrace();
			 } 
		 }
	      
		 return contacts;
	 }
}
