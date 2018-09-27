package exceptions;

import interfaces.IErrorCode;
import interfaces.IErrorDetail;

/**
 * Exception to be thrown when some business-related exception situation occurs while communicating via REST.
 *
 */
public class BusinessException extends Exception {

    private static final long serialVersionUID = 250222775653227080L;

    private IErrorCode errorCode;
    private IErrorDetail[] errorDetails;
    private String target;
    private boolean forwarded;

    private BusinessException(IErrorCode errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
    }

    private BusinessException(IErrorCode errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
    }

    public IErrorCode getErrorCode() {
        return errorCode;
    }

    public IErrorDetail[] getErrorDetails() {
        return errorDetails;
    }

    public BusinessException details(IErrorDetail... errorDetails) {
        this.errorDetails = errorDetails;
        return this;
    }

    public boolean isForwarded() {
        return forwarded;
    }

    public BusinessException forwarded(boolean forwarded) {
        this.forwarded = forwarded;
        return this;
    }

    public String getTarget() {
        return target;
    }

    public BusinessException target(String target) {
        this.target = target;
        return this;
    }

    public static BusinessException create(IErrorCode errorCode, String message, Object... arguments) {
        return new BusinessException(errorCode, String.format(message, arguments));
    }

    public static BusinessException create(IErrorCode errorCode, String message) {
        return new BusinessException(errorCode, message);
    }

    public static BusinessException create(IErrorCode errorCode, Throwable cause, String message, Object... arguments) {
        return new BusinessException(errorCode, String.format(message, arguments), cause);
    }

    public static BusinessException create(IErrorCode errorCode, Throwable cause, String message) {
        return new BusinessException(errorCode, message, cause);
    }

}
