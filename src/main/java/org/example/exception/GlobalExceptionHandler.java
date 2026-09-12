package org.example.exception;

import org.example.dto.ErrorResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CampeonatoInvalidoException.class)
    public ResponseEntity<ErrorResponseDTO> handleCampeonatoInvalido(CampeonatoInvalidoException ex) {
        ErrorResponseDTO errorDTO = new ErrorResponseDTO(
                HttpStatus.BAD_REQUEST.value(),
                "Regla de Negocio Rota",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
    }

    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<ErrorResponseDTO> handleEstadoIlegal(IllegalStateException ex) {
        ErrorResponseDTO errorDTO = new ErrorResponseDTO(
                HttpStatus.CONFLICT.value(),
                "Estado Inconsistente",
                ex.getMessage(),
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.CONFLICT).body(errorDTO);
    }
}