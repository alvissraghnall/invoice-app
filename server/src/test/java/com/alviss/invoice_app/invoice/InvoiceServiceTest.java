package com.alviss.invoice_app.invoice;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InvoiceServiceTest {

    @Mock
    private InvoiceRepository invoiceRepository;

    @InjectMocks
    private InvoiceService invoiceService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateInvoice() {
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Initialize with necessary fields

        Invoice invoice = new Invoice(); // Initialize with necessary fields
        when(invoiceRepository.save(any())).thenReturn(invoice);

        InvoiceDTO result = invoiceService.createInvoice(invoiceDTO);

        verify(invoiceRepository).save(any());
        // Assert the mapping and return value
        // assertEquals(expectedResult, result);
    }

    @Test
    void testGetInvoice() {
        String invoiceId = "invoiceId";
        Invoice invoice = new Invoice(); // Initialize with necessary fields
        when(invoiceRepository.findById(invoiceId)).thenReturn(Optional.of(invoice));

        InvoiceDTO result = invoiceService.getInvoice(invoiceId);

        verify(invoiceRepository).findById(invoiceId);
        // Assert the mapping and return value
        // assertEquals(expectedResult, result);
    }

    @Test
    void testGetAllInvoices() {
        Invoice invoice = new Invoice(); // Initialize with necessary fields
        when(invoiceRepository.findAll()).thenReturn(Collections.singletonList(invoice));

        List<InvoiceDTO> result = invoiceService.getAllInvoices();

        verify(invoiceRepository).findAll();
        // Assert the mapping and return value
        // assertEquals(expectedResult, result);
    }

    @Test
    void testReplaceInvoice() {
        String invoiceId = "invoiceId";
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Initialize with necessary fields

        Invoice invoice = new Invoice(); // Initialize with necessary fields
        when(invoiceRepository.save(any())).thenReturn(invoice);

        InvoiceDTO result = invoiceService.replaceInvoice(invoiceId, invoiceDTO);

        verify(invoiceRepository).save(any());
        // Assert the mapping and return value
        // assertEquals(expectedResult, result);
    }

    @Test
    void testDeleteInvoice() {
        String invoiceId = "invoiceId";

        invoiceService.deleteInvoice(invoiceId);

        verify(invoiceRepository).deleteById(invoiceId);
    }

}
