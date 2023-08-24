package com.alviss.invoice_app.invoice;

import com.alviss.invoice_app.common.ValidateEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.ZoneId;
// import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class InvoiceDTO {

    private String id;

    @NotBlank(message = "Biller Street Address must be provided!")
    private String billerStreetAddress;

    @NotBlank(message = "Biller City must be provided!")
    private String billerCity;

    @NotNull(message = "Biller Zip Code must be provided!")
    private int billerZipCode;

    @NotBlank(message = "Biller Country must be provided!")
    private String billerCountry;

    @NotBlank(message = "Client Name must be provided!")
    private String clientName;

    @NotBlank(message = "Client Street Address must be provided!")
    private String clientStreetAddress;

    @NotBlank(message = "Client Email must be provided!")
    private String clientEmail;

    @NotBlank(message = "Client City must be provided!")
    private String clientCity;

    @NotNull(message = "Client Zip Code must be provided!")
    private int clientZipCode;

    @NotBlank(message = "Client Country must be provided!")
    private String clientCountry;

    private LocalDate invoiceDate = LocalDate.now(
        ZoneId.of("Africa/Lagos")
    );

    @NotNull(message = "Payment Terms must be provided!")
    private int paymentTerms;

    private LocalDate paymentDueDate;

    private String productDesc;

    @ValidateEnum(targetClassType = InvoiceStatus.class, message = "Invalid status provided.")
    private String status = "PENDING";

    @NotNull(message = "Invoice items must be provided!")
    @NotEmpty(message = "Invoice items must not be null!")
    @Valid
    private List<InvoiceItemDTO> invoiceItemList;

}
