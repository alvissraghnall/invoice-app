package com.alviss.invoice_app.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FieldError {

    private String field;
    private String errorCode;
    private String message;

}

