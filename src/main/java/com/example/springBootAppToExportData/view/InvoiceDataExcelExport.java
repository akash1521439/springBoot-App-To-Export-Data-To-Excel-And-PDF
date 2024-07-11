package com.example.springBootAppToExportData.view;

import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.example.springBootAppToExportData.model.Invoice;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InvoiceDataExcelExport extends AbstractXlsxView{

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 
		//Define file name
		response.addHeader("Content-Disposition", "attachment;fileName=invoiceData.xlsx");
		
		//Read data privided
		@SuppressWarnings("unchecked")
		List<Invoice> list = (List<Invoice>) model.get("list");
		
		//Create one sheet
		Sheet sheet = workbook.createSheet("Invoice");
		
		//create row as header
		Row row0 = sheet.createRow(0);
		row0.createCell(0).setCellValue("ID");
		row0.createCell(1).setCellValue("NAME");
	    row0.createCell(2).setCellValue("LOCATION");
	    row0.createCell(3).setCellValue("AMOUNT");
	    
	       // create row1 onwards from List<T>
	       int rowNum = 1;
	       for(Invoice spec : list) {
	           Row row = sheet.createRow(rowNum++);
	           row.createCell(0).setCellValue(spec.getId());
	           row.createCell(1).setCellValue(spec.getName());
	           row.createCell(2).setCellValue(spec.getLocation());
	           row.createCell(3).setCellValue(spec.getAmount());
	       }
		
	}

}
