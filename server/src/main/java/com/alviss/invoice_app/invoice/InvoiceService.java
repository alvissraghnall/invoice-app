package com.alviss.invoice_app.invoice;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    InvoiceDTO createInvoice (InvoiceDTO invoiceDTO) {
        final Invoice invoice = new Invoice();
        mapToEntity(invoiceDTO, invoice);
        if (invoice.getPaymentDueDate() == null) {

            invoice.setPaymentDueDate(invoiceDTO.getInvoiceDate().plus(
                Period.ofDays(invoiceDTO.getPaymentTerms())
            ));
        }
        Invoice savedInvoice = invoiceRepository.save(invoice);

        System.out.println(savedInvoice.getPaymentDueDate().plusDays(22));
        
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

    void deleteInvoice (final String id) {
        invoiceRepository.deleteById(id);
    }

    private Invoice mapToEntity(final InvoiceDTO invoiceDTO, final Invoice invoice) {
        invoice.setId(invoiceDTO.getId());
        invoice.setBillerCity(invoiceDTO.getBillerCity());
        invoice.setBillerCountry(invoiceDTO.getBillerCountry());
        invoice.setBillerZipCode(invoiceDTO.getBillerZipCode());
        invoice.setBillerStreetAddress(invoiceDTO.getBillerStreetAddress());
        invoice.setClientCity(invoiceDTO.getClientCity());
        invoice.setClientCountry(invoiceDTO.getClientCountry());
        invoice.setClientStreetAddress(invoiceDTO.getClientStreetAddress());
        invoice.setClientZipCode(invoiceDTO.getClientZipCode());
        invoice.setClientEmail(invoiceDTO.getClientEmail());
        invoice.setClientName(invoiceDTO.getClientName());
        invoice.setProductDesc(invoiceDTO.getProductDesc());

        if (invoiceDTO.getStatus() != null) {
            invoice.setStatus(InvoiceStatus.valueOf(invoiceDTO.getStatus()));
        };
        invoice.setInvoiceDate(invoiceDTO.getInvoiceDate());
        invoice.setInvoiceItemList(
            invoiceDTO.getInvoiceItemList().stream().map(
                item -> mapInvoiceDTOToItem(item, new InvoiceItem())
            ).collect(Collectors.toList())
        );
        invoice.setPaymentDueDate(invoiceDTO.getPaymentDueDate());
        invoice.setPaymentTerms(invoiceDTO.getPaymentTerms());
        return invoice;
    }

    private InvoiceDTO mapToDTO(final Invoice invoice, final InvoiceDTO invoiceDTO) {
        invoiceDTO.setId(invoice.getId());
        invoiceDTO.setBillerCountry(invoice.getBillerCountry());
        invoiceDTO.setBillerCity(invoice.getBillerCity());
        invoiceDTO.setBillerZipCode(invoice.getBillerZipCode());
        invoiceDTO.setBillerStreetAddress(invoice.getBillerStreetAddress());
        invoiceDTO.setClientCity(invoice.getClientCity());
        invoiceDTO.setClientCountry(invoice.getClientCountry());
        invoiceDTO.setClientZipCode(invoice.getClientZipCode());
        invoiceDTO.setClientStreetAddress(invoice.getClientStreetAddress());
        invoiceDTO.setClientEmail(invoice.getClientEmail());
        invoiceDTO.setClientName(invoice.getClientName());

        invoiceDTO.setProductDesc(invoice.getProductDesc());
        invoiceDTO.setStatus(invoice.getStatus().toString());
        invoiceDTO.setInvoiceDate(invoice.getInvoiceDate());
        invoiceDTO.setInvoiceItemList(
            invoice.getInvoiceItemList().stream().map(
                item -> mapInvoiceItemToDTO(item, new InvoiceItemDTO())
            ).collect(Collectors.toList())
        );
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
