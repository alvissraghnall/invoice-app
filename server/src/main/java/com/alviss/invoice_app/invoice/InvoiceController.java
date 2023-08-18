package com.alviss.invoice_app.invoice;

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/invoice")
//@CrossOrigin(origins = "http://localhost:5000", maxAge = 3600)
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = "creates a new invoice", responseCode = "201")
    public ResponseEntity<InvoiceDTO> createInvoice (
            @Valid @RequestBody InvoiceDTO invoiceDTO,
            final HttpServletRequest request
    ) {
        return ResponseEntity.created(URI.create(getAppURL(request))).body(invoiceService.createInvoice(invoiceDTO));
    }

    @GetMapping(value = "/{id}")
    @ApiResponse(description = "Gets an invoice item", responseCode = "200")
    public ResponseEntity<InvoiceDTO> getInvoice (@PathVariable String id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @GetMapping
    @ApiResponse(description = "Gets all invoices", responseCode = "200")
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices () {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @PutMapping("/{id}")
    @ApiResponse(description = "Replaces an invoice item", responseCode = "204")
    public ResponseEntity<?> replaceById (
            @PathVariable String id,
            @RequestBody InvoiceDTO invoiceDTO
    ) {
        invoiceService.replaceInvoice(
                id, invoiceDTO
        );
        return ResponseEntity.noContent().build();
    }

    private String getAppURL (final HttpServletRequest req) {
        return "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }
}
