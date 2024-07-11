package com.example.springBootAppToExportData.service;

import java.util.List;

import com.example.springBootAppToExportData.model.Invoice;

public interface InvoiceService {
	
	public Invoice saveInvoice(Invoice invoice);
	public List<Invoice> getAllInvoices();
	public Invoice getInvoiceById(Long id);
    public void deleteInvoiceById(Long id);
    public void updateInvoice(Invoice invoice);

}
