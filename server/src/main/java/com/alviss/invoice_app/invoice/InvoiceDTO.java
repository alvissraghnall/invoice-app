package com.alviss.invoice_app.invoice;

import com.alviss.invoice_app.common.ValidateEnum;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class InvoiceDTO {

    private String id;

    private String billerStreetAddress;

    private String billerCity;

    private int billerZipCode;

    private String billerCountry;

    private String clientName;

    private String clientStreetAddress;

    private String clientEmail;

    private String clientCity;

    private int clientZipCode;

    private String clientCountry;

    private Date invoiceDate;

    private int paymentTerms;

    private Date paymentDueDate;

    private String productDesc;

    @ValidateEnum(targetClassType = InvoiceStatus.class, message = "Invalid status provided.")
    private String status;

    @NotNull(message = "Invoice items must be provided!")
    @NotEmpty(message = "Invoice items must not be null!")
    @Valid
    private List<InvoiceItemDTO> invoiceItemList;

}