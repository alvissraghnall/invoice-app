package com.alviss.invoice_app.common;

import com.alviss.invoice_app.common.RestExceptionHandler;
import com.alviss.invoice_app.common.ErrorResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class RestExceptionHandlerTest {

    @InjectMocks
    private RestExceptionHandler restExceptionHandler;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testHandleHttpMediaTypeNotSupported() {
        HttpMediaTypeNotSupportedException ex = new HttpMediaTypeNotSupportedException("Invalid media type");
        WebRequest request = mock(WebRequest.class);

        ResponseEntity<ErrorResponse> responseEntity = restExceptionHandler.handleHttpMediaTypeNotSupported(ex, request);

        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), errorResponse.getHttpStatus());
        assertEquals(ex.getClass().getSimpleName(), errorResponse.getException());
        assertEquals("Invalid Content-Type passed in request. Acceptable type is 'application/json'", errorResponse.getMessage());
    }

    @Test
    public void testHandleMethodArgumentNotValid() {
        MethodArgumentNotValidException ex = new MethodArgumentNotValidException(null, null);
        ResponseEntity<ErrorResponse> responseEntity = restExceptionHandler.handleMethodArgumentNotValid(ex);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResponse errorResponse = responseEntity.getBody();
        assertEquals(HttpStatus.BAD_REQUEST.value(), errorResponse.getHttpStatus());
        assertEquals(ex.getClass().getSimpleName(), errorResponse.getException());
        // more assertions for other properties in the error response
    }


}
