package com.modista.exceptions;

public class ModistaException extends RuntimeException {
    
    public ModistaException(String message) {
        super(message);
    }

    public ModistaException(String message, Throwable cause) {
        super(message, cause);
    }
    
    public static class DatabaseException extends ModistaException {
        public DatabaseException(String message) {
            super(message);
        }
        
        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }
    
    public static class ValidationException extends ModistaException {
        public ValidationException(String message) {
            super(message);
        }
    }
}
