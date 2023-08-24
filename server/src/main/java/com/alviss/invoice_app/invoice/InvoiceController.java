package com.alviss.invoice_app.invoice;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name="Invoice", description = "Invoice management APIs")
// @CrossOrigin(origins = "http://localhost:5000", maxAge = 3600)
@RequiredArgsConstructor
public class InvoiceController {

    private final InvoiceService invoiceService;

    @Operation(
        summary = "Create new invoice",
        description = "Create a new invoice by specifying required, and/or optional fields in request body.",
        tags = { "invoice", "post" }
    )
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiResponse(description = "creates a new invoice", responseCode = "201", content = { @Content(schema = @Schema(implementation = InvoiceDTO.class), mediaType = "application/json") })
    public ResponseEntity<InvoiceDTO> createInvoice (
            @Valid @RequestBody InvoiceDTO invoiceDTO,
            final HttpServletRequest request
    ) {
        return ResponseEntity.created(URI.create(getAppURL(request))).body(invoiceService.createInvoice(invoiceDTO));
    }

    @Operation(
        summary = "Gets an invoice",
        description = "Gets an invoice by specified ID provided in path as variable.",
        tags = { "invoice", "get" }
    )
    @GetMapping(value = "/{id}")
    @ApiResponse(description = "Gets an invoice", responseCode = "200", content = { @Content(schema = @Schema(implementation = InvoiceDTO.class), mediaType = "application/json") })
    public ResponseEntity<InvoiceDTO> getInvoice (@PathVariable String id) {
        return ResponseEntity.ok(invoiceService.getInvoice(id));
    }

    @GetMapping
    @Operation(
        summary = "Gets all invoices",
        description = "Gets all invoices.",
        tags = { "invoice", "get" }
    )
    @ApiResponse(description = "Gets all invoices", responseCode = "200", content = { @Content(schema = @Schema(implementation = InvoiceDTO.class), mediaType = "application/json") })
    public ResponseEntity<List<InvoiceDTO>> getAllInvoices () {
        return ResponseEntity.ok(invoiceService.getAllInvoices());
    }

    @Operation(
        summary = "Updates an invoice",
        description = "Updates an invoice via specified ID in path as variable, and parts of the entity to be updated, provided in response body.",
        tags = { "invoice", "put" }
    )
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

    @Operation(
        summary = "Deletes an invoice",
        description = "Deletes an invoice via specified ID provided in path as variable.",
        tags = { "invoice", "delete" }
    )
    @DeleteMapping("/{id}")
    @ApiResponse(description = "Deletes an invoice item", responseCode = "204")
    public ResponseEntity<?> removeById (
            @PathVariable String id
    ) {
        invoiceService.deleteInvoice(
            id
        );
        return ResponseEntity.noContent().build();
    }
    

    private String getAppURL (final HttpServletRequest req) {
        return "http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath();
    }
}
