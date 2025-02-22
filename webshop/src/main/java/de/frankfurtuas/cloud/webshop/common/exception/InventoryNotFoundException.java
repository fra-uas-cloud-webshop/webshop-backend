package de.frankfurtuas.cloud.webshop.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Inventory not found")
public class InventoryNotFoundException extends RuntimeException {

    /**
     * Constructor.
     *
     * @param message the custom message
     */
    public InventoryNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructor.
     *
     * @param message the custom message
     * @param cause   the cause
     */
    public InventoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}