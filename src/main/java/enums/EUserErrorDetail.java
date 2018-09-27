package enums;

import interfaces.IErrorDetail;

public enum EUserErrorDetail implements IErrorDetail {

    USERS_USERNAME_NOT_FOUND("The provided username is not found in database", "username"),
    USERS_USERNAME_INVALID("The provided username is invalid", "username"),
    USERS_USERNAME_ALREADY_EXISTS("The provided username already exists", "username"),

    USER_PASSWORD_COULD_NOT_BE_HASHED("The provided user password could not be hashed","username"),
    USER_PASSWORD_CAN_NOT_BE_EMPTY("The provided user password can not be empty","username"),
    USER_PASSWORD_INCORRECT("The provided user password is incorrect","username"),

    USERS_USERNAME_MISSING("The username is missing", "username"),
    USERS_CODE_MISSING("The code is missing", "code");

    private String message;

    private String target;

    EUserErrorDetail(String message, String target) {
        this.message = message;
        this.target = target;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String getCode() {
        return name().toLowerCase();
    }

    public String getTarget() {
        return target;
    }

}

