package enums;

import interfaces.IErrorCode;

public enum EGeneralErrorCode implements IErrorCode {

    BAD_REQUEST(400),
    APPLICATION_ERROR(500),
    RECEIVED_ERROR_RESPONSE(500);

    private int statusCode;

    EGeneralErrorCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    @Override
    public int getStatusCode() {
        return statusCode;
    }

}

