package com.alviss.invoice_app.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    InvoiceDTO createInvoice (InvoiceDTO invoiceDTO) {
        final Invoice invoice = new Invoice();
        mapToEntity(invoiceDTO, invoice);
        Invoice savedInvoice = invoiceRepository.save(invoice);

        return mapToDTO(savedInvoice, new InvoiceDTO());
    }

    private Invoice mapToEntity(final InvoiceDTO invoiceDTO, final Invoice invoice) {
        invoice.setBillerCity(invoiceDTO.getBillerCity());
        invoice.setBillerCountry(invoiceDTO.getBillerCountry());
        invoice.setBillerCity(invoiceDTO.getBillerCity());
        invoice.setBillerZipCode(invoiceDTO.getBillerZipCode());
        invoice.setClientCity(invoiceDTO.getClientCity());
        invoice.setClientCountry(invoiceDTO.getClientCountry());
        invoice.setClientCity(invoiceDTO.getClientCity());
        invoice.setClientZipCode(invoiceDTO.getClientZipCode());
        invoice.setClientEmail(invoiceDTO.getClientEmail());
        invoice.setClientName(invoiceDTO.getClientName());

        invoice.setStatus(InvoiceStatus.valueOf(invoiceDTO.getStatus()));
        invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setInvoiceItemList(invoiceDTO.getInvoiceItemList());
        invoice.setPaymentDueDate(invoiceDTO.getPaymentDueDate());
        invoice.setPaymentTerms(invoiceDTO.getPaymentTerms());
        invoice.setPaymentDueDate(invoiceDTO.getPaymentDueDate());
        return invoice;
    }

    private InvoiceDTO mapToDTO(final Invoice invoice, final InvoiceDTO invoiceDTO) {
        invoiceDTO.setBillerCountry(invoice.getBillerCountry());
        invoiceDTO.setBillerCity(invoice.getBillerCity());
        invoiceDTO.setBillerZipCode(invoice.getBillerZipCode());
        invoiceDTO.setBillerCity(invoice.getBillerCity());
        invoiceDTO.setClientCity(invoice.getClientCity());
        invoiceDTO.setClientCountry(invoice.getClientCountry());
        invoiceDTO.setClientCity(invoice.getClientCity());
        invoiceDTO.setClientZipCode(invoice.getClientZipCode());
        invoiceDTO.setClientEmail(invoice.getClientEmail());
        invoiceDTO.setClientName(invoice.getClientName());

        invoiceDTO.setStatus(invoice.getStatus().toString());
        invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());
        invoiceDTO.setInvoiceItemList(invoice.getInvoiceItemList());
        invoiceDTO.setPaymentDueDate(invoice.getPaymentDueDate());
        invoiceDTO.setPaymentTerms(invoice.getPaymentTerms());
        invoiceDTO.setPaymentDueDate(invoice.getPaymentDueDate());
        return invoiceDTO;
    }

}
