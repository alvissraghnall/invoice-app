package com.alviss.invoice_app.invoice;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InvoiceItem {

    private String id;

    private String itemName;

    private int qty;

    private BigDecimal price;

}
