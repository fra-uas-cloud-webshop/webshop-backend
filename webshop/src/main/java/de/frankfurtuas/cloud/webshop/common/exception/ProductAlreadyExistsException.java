package de.frankfurtuas.cloud.webshop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for when a product already exists.
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Product with this name already exists")
public class ProductAlreadyExistsException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message the custom message
     */
    public ProductAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message the custom message
     * @param cause   the cause
     */
    public ProductAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}
