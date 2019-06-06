package com.contactmgr.misc;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.contactmgr.data.Contact;

public class ExcelExporter {

	private static String[] columns = { "Email" };

	public static Boolean exportContactsToExcel(List<Contact> contacts) {
		
		Workbook workbook = null;
		FileOutputStream fileOut = null;
		Boolean exportSuccess = false;
		try {

			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Contacts");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 14);
			headerFont.setColor(IndexedColors.RED.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Create a Row
			Row headerRow = sheet.createRow(0);

			for (int i = 0; i < columns.length; i++) {
				Cell cell = headerRow.createCell(i);
				cell.setCellValue(columns[i]);
				cell.setCellStyle(headerCellStyle);
			}

			// Create Other rows and cells with contacts data
			int rowNum = 1;

			for (Contact contact : contacts) {
				Row row = sheet.createRow(rowNum++);
				row.createCell(0).setCellValue(contact.getEmail());
			}

			// Resize all columns to fit the content size
			for (int i = 0; i < columns.length; i++) {
				sheet.autoSizeColumn(i);
			}

			// Write the output to a file
			fileOut = new FileOutputStream("export-dir/exported-contacts.xlsx");
			workbook.write(fileOut);
			
			exportSuccess = true;

		} catch (Exception e) {
			e.printStackTrace();
			exportSuccess = false;
			
		} finally {
			try {
				workbook.close();
				fileOut.close();
			} catch (Exception e) {
				e.printStackTrace();
				exportSuccess = false;
			}
		}
		
		return exportSuccess;
	}
	
	/*public static void main(String[] args) {
		List<Contact> contacts = new ArrayList<Contact>();
		contacts.add(new Contact("sdasddasd"));
		ExcelExporter.exportContactsToExcel(contacts);
	}*/

}
