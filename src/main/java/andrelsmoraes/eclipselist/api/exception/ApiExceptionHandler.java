package andrelsmoraes.eclipselist.api.exception;

import andrelsmoraes.eclipselist.api.response.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.elasticsearch.NoSuchIndexException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.time.LocalDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ApiExceptionHandler.class);

    /**
     * Handle org.springframework.data.elasticsearch.NoSuchIndexException
     * Return HTTP 404 (Not Found) when a specific Elasticsearch index does not exist.
     */
    @ExceptionHandler(NoSuchIndexException.class)
    public ResponseEntity<Object> handleNoSuchIndexException(NoSuchIndexException ex) {
        logger.error("NoSuchIndexException occurred: {}", ex.getMessage());
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                "Index Not Found",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    /**
     * Handle MethodArgumentTypeMismatchException
     * Return HTTP 400 (Bad Request) for argument type mismatch exceptions.
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatchException(RuntimeException ex) {
        logger.error("MethodArgumentTypeMismatchException occurred: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                "An argument error occurred. Please check dates, types and UUIDs. in your request.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle RuntimeException
     * Return HTTP 500 (Internal Server Error) for all generic runtime exceptions.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(RuntimeException ex) {
        logger.error("RuntimeException occurred: {}", ex.getMessage(), ex);
        ErrorResponse response = new ErrorResponse(
                LocalDateTime.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred. Please contact support.",
                ex.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
