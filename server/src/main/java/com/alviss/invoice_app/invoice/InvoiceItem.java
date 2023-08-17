package com.alviss.invoice_app.invoice;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InvoiceItem {

    private String id;

    private String itemName;

    private float qty;

    private float price;

}
