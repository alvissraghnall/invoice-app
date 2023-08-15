package com.alviss.invoice_app.invoice;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Invoice {

    @Id
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

    private InvoiceStatus status = InvoiceStatus.PENDING;

    private InvoiceItem[] invoiceItemList;
    
}
