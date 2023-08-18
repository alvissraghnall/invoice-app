package com.alviss.invoice_app.invoice;


import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = false)
public class InvoiceItemDTO {

    @NotEmpty(message = "Invoice Item ID must not be empty")
    private String id;

    @NotEmpty(message = "Item Name must not be empty.")
    @NotBlank(message = "Item Name must not be blank.")
    private String itemName;

    @NotNull
    @Positive
    private int qty;

    @NotNull
    @PositiveOrZero
    private BigDecimal price;

}
