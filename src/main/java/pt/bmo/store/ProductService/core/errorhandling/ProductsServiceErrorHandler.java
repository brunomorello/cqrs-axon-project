package pt.bmo.store.ProductService.core.errorhandling;

import org.axonframework.commandhandling.CommandExecutionException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class ProductsServiceErrorHandler {

    @ExceptionHandler(value = IllegalStateException.class)
    public ResponseEntity handleIllegalStateException(IllegalStateException ex, WebRequest request) {
        ErrorMsg errorMsg = new ErrorMsg(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity(errorMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception ex, WebRequest request) {
        ErrorMsg errorMsg = new ErrorMsg(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity(errorMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = CommandExecutionException.class)
    public ResponseEntity handleCommandExecutionException(CommandExecutionException ex, WebRequest request) {
        ErrorMsg errorMsg = new ErrorMsg(LocalDateTime.now(), ex.getMessage());
        return new ResponseEntity(errorMsg, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
