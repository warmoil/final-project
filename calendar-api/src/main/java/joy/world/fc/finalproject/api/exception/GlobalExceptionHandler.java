package joy.world.fc.finalproject.api.exception;

import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;
import lombok.Data;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CalendarException.class)
    public ResponseEntity<ErrorResponse> handle(CalendarException e) {
        final ErrorCode errorCode = e.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode, errorCode.getMessage()), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException e) {
        final ErrorCode errorCode = ErrorCode.VALIDATION_FAIL;
        return new ResponseEntity<>(new ErrorResponse(
                errorCode,
                Optional.ofNullable(e.getBindingResult().getFieldError())
                        .map(DefaultMessageSourceResolvable::getDefaultMessage)
                        .orElse(errorCode.getMessage())
        ), ErrorHttpStatusMapper.mapToStatus(errorCode));
    }

    @Data
    public static class ErrorResponse {
        private final ErrorCode errorCode;
        private final String errorMessage;
    }

}
