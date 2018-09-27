package exceptions;

import interfaces.IErrorCode;

public class ErrorResponse {
    private IErrorCode status;
    private String errorCode;
    private String message;
    public IErrorCode getStatus() {
        return status;
    }
    public void setStatus(IErrorCode status) {
        this.status = status;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

}
