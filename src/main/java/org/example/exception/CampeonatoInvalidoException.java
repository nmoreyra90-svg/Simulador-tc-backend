package org.example.exception;

public class CampeonatoInvalidoException extends RuntimeException {
    public CampeonatoInvalidoException(String message) {
        super(message);
    }
}