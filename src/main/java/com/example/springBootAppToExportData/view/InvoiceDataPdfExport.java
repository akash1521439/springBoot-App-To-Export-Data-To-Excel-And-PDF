package com.example.springBootAppToExportData.view;

import java.awt.Color;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.example.springBootAppToExportData.model.Invoice;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class InvoiceDataPdfExport extends AbstractPdfView{

	
	
	@Override
	protected void buildPdfMetadata(Map<String, Object> model, Document document, HttpServletRequest request) {
		
		Font headerFont = new Font(Font.TIMES_ROMAN,20,Font.BOLD,Color.MAGENTA);
		HeaderFooter header = new HeaderFooter(new Phrase("All invoices PDF view",headerFont), false);
		header.setAlignment(Element.ALIGN_CENTER);
		document.setHeader(header);
		
		Font dateFont = new Font(Font.HELVETICA,12,Font.NORMAL,Color.BLUE);
		
		HeaderFooter footer = new HeaderFooter(new Phrase("PDF Export Executed On : "+new Date(), dateFont), true);
		footer.setAlignment(Element.ALIGN_CENTER);
		document.setFooter(footer);
	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		//Give a pdf file name
		response.addHeader("Content-Disposition", "attachment;filename=invoices.pdf");
		
		//Read data from controller
		List<Invoice> list = (List<Invoice>) model.get("list");
		
		//Create element
		Font titleFont = new Font(Font.TIMES_ROMAN,24,Font.BOLD,Color.blue);
		Paragraph title = new Paragraph("ALL INVOICES DATA", titleFont);
		title.setAlignment(Element.ALIGN_CENTER);
	    title.setSpacingBefore(20.0f);
	    title.setSpacingAfter(25.0f);
	    
	    //add to document
	    document.add(title);
	    
		Font tableHead = new Font(Font.TIMES_ROMAN, 12, Font.BOLD, Color.blue);
		PdfPTable table = new PdfPTable(4);// no.of columns
		table.addCell(new Phrase("ID", tableHead));
		table.addCell(new Phrase("NAME", tableHead));
		table.addCell(new Phrase("LOCATION", tableHead));
		table.addCell(new Phrase("AMOUNT", tableHead));

		for (Invoice invoice : list) {
			table.addCell(invoice.getId().toString());
			table.addCell(invoice.getName());
			table.addCell(invoice.getLocation());
			table.addCell(invoice.getAmount().toString());
		}
		// add table data to document
		document.add(table);
		
	}

}
