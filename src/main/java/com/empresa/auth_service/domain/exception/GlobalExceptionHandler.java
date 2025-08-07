package com.empresa.auth_service.domain.exception;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.empresa.auth_service.adapter.in.web.dto.response.ErrorDTO;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private static final Logger authAuditLogger = LoggerFactory.getLogger("AUTH_AUDIT");

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex, WebRequest request) {
        logger.warn("Error de validación en endpoint: {} - Errores: {}",
            request.getDescription(false),
            ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .toList()
        );

        ErrorDTO errorDTO = new ErrorDTO();
        String errorMessage = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .findFirst()
            .map(error -> error.getDefaultMessage())
            .orElse("Error de validación");
        errorDTO.setMessage(errorMessage);
        errorDTO.setStatus(HttpStatus.BAD_REQUEST.value());
        errorDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.badRequest().body(errorDTO);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<Map<String, String>> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
        String clientInfo = getClientInfo(request);
        authAuditLogger.warn("Intento de login fallido - IP: {} - Endpoint: {} - Mensaje: {}", 
            clientInfo, request.getDescription(false), ex.getMessage());

        Map<String, String> response = new HashMap<>();
        response.put("error", ex.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDTO> handleGenericException(Exception ex, WebRequest request) {
        logger.error("Error no controlado en endpoint: {} - Error: {}",
            request.getDescription(false), ex.getMessage(), ex);

        ErrorDTO errorDTO = new ErrorDTO();
        errorDTO.setMessage("Error interno del servidor");
        errorDTO.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        errorDTO.setTimestamp(String.valueOf(System.currentTimeMillis()));
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
    }

    /**
     * Extrae información del cliente (IP) de la request
     */
    private String getClientInfo(WebRequest request) {
        String xForwardedFor = request.getHeader("X-Forwarded-For");
        if (xForwardedFor != null && !xForwardedFor.isEmpty()) {
            return xForwardedFor.split(",")[0].trim();
        }
        String xRealIp = request.getHeader("X-Real-IP");
        if (xRealIp != null && !xRealIp.isEmpty()) {
            return xRealIp;
        }
        return "IP no disponible";
    }

}
