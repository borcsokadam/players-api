package src.exceptions;

import dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleException(MethodArgumentNotValidException ex) {
        FieldError error = ex.getFieldErrors().get(0);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(String.format("Invalid value for %s: %s", error.getField(), error.getRejectedValue())));
    }

    @ExceptionHandler(value = AuthenticationException.class)
    public ResponseEntity<?> handleException(AuthenticationException ex) {
        return ResponseEntity.status(ex.getStatus()).body(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<?> handleException(EntityNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(ex.getMessage()));
    }
}
