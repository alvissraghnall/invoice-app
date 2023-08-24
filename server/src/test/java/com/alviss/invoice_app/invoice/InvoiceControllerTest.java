package com.alviss.invoice_app.invoice;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.alviss.invoice_app.invoice.*;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(InvoiceController.class)
class InvoiceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private InvoiceService invoiceService;

    @Test
    void testCreateInvoice() throws Exception {
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Initialize with necessary fields
        when(invoiceService.createInvoice(any())).thenReturn(invoiceDTO);

        mockMvc.perform(MockMvcRequestBuilders.post("/invoice")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testGetInvoice() throws Exception {
        String invoiceId = "invoiceId"; // Replace with actual invoice ID
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Initialize with necessary fields
        when(invoiceService.getInvoice(invoiceId)).thenReturn(invoiceDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/invoice/{id}", invoiceId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists());
    }

    @Test
    void testGetAllInvoices() throws Exception {
        List<InvoiceDTO> invoiceDTOList = new ArrayList<>(); // Initialize with necessary fields
        when(invoiceService.getAllInvoices()).thenReturn(invoiceDTOList);

        mockMvc.perform(MockMvcRequestBuilders.get("/invoice"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
    }

    @Test
    void testReplaceInvoice() throws Exception {
        String invoiceId = "invoiceId"; // Replace with actual invoice ID
        InvoiceDTO invoiceDTO = new InvoiceDTO(); // Initialize with necessary fields

        mockMvc.perform(MockMvcRequestBuilders.put("/invoice/{id}", invoiceId)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invoiceDTO)))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void testRemoveInvoice() throws Exception {
        String invoiceId = "invoiceId"; // Replace with actual invoice ID

        mockMvc.perform(MockMvcRequestBuilders.delete("/invoice/{id}", invoiceId))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }
}
