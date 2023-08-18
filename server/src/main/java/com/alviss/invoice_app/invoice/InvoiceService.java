package com.alviss.invoice_app.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    InvoiceDTO getInvoice (final String id) {
        return mapToDTO(
                invoiceRepository.findById(id).orElseThrow(),
                new InvoiceDTO()
        );
    }

    List<InvoiceDTO> getAllInvoices () {
        return invoiceRepository.findAll().stream().map(
                invoice -> mapToDTO(invoice, new InvoiceDTO())
        ).collect(Collectors.toList());
    }

    InvoiceDTO replaceInvoice (final String id, InvoiceDTO invoiceDTO) {
        final Invoice invoice = mapToEntity(invoiceDTO, new Invoice());
        invoice.setId(id);
        return mapToDTO(invoiceRepository.save(invoice), new InvoiceDTO());
    }

    private Invoice mapToEntity(final InvoiceDTO invoiceDTO, final Invoice invoice) {
        invoice.setId(invoiceDTO.getId());
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
        invoice.setInvoiceItemList(
            invoiceDTO.getInvoiceItemList().stream().map(
                item -> mapInvoiceDTOToItem(item, new InvoiceItem())
            ).collect(Collectors.toList())
        );
        invoice.setPaymentDueDate(invoiceDTO.getPaymentDueDate());
        invoice.setPaymentTerms(invoiceDTO.getPaymentTerms());
        invoice.setPaymentDueDate(invoiceDTO.getPaymentDueDate());
        return invoice;
    }

    private InvoiceDTO mapToDTO(final Invoice invoice, final InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoice.getId());
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
        invoiceDTO.setInvoiceItemList(
            invoice.getInvoiceItemList().stream().map(
                item -> mapInvoiceItemToDTO(item, new InvoiceItemDTO())
            ).collect(Collectors.toList())
        );
        invoiceDTO.setPaymentDueDate(invoice.getPaymentDueDate());
        invoiceDTO.setPaymentTerms(invoice.getPaymentTerms());
        invoiceDTO.setPaymentDueDate(invoice.getPaymentDueDate());
        return invoiceDTO;
    }

    private InvoiceItem mapInvoiceDTOToItem (
        final InvoiceItemDTO invoiceItemDTO,
        final InvoiceItem invoiceItem
    ) {
        invoiceItem.setId(invoiceItemDTO.getId());
        invoiceItem.setQty(invoiceItemDTO.getQty());
        invoiceItem.setPrice(invoiceItemDTO.getPrice());
        invoiceItem.setItemName(invoiceItemDTO.getItemName());

        return invoiceItem;
    }

    private InvoiceItemDTO mapInvoiceItemToDTO (
        final InvoiceItem invoiceItem,
        final InvoiceItemDTO invoiceItemDTO
    ) {
        invoiceItemDTO.setId(invoiceItem.getId());
        invoiceItemDTO.setQty(invoiceItem.getQty());
        invoiceItemDTO.setPrice(invoiceItem.getPrice());
        invoiceItemDTO.setItemName(invoiceItem.getItemName());

        return invoiceItemDTO;
    }

}
