package exceptions;

import enums.EGeneralErrorCode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception ex) {
        ErrorResponse error = new ErrorResponse();
        error.setStatus(EGeneralErrorCode.RECEIVED_ERROR_RESPONSE);
        error.setErrorCode(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        error.setMessage("Please contact administrator, This is internal server error");
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.OK);
    }
}
