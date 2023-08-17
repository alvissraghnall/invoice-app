package com.alviss.invoice_app.invoice;


import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class InvoiceItemDTO {

    @NotEmpty
    private String id;

    @NotEmpty
    private String itemName;

    @NotEmpty
    private float qty;

    @NotEmpty
    private float price;

}
