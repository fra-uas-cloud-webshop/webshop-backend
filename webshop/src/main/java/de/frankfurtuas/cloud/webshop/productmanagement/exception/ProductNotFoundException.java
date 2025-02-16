package de.frankfurtuas.cloud.webshop.productmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product not found")
public class ProductNotFoundException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message the custom message
     */
    public ProductNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message the custom message
     * @param cause   the cause
     */
    public ProductNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}