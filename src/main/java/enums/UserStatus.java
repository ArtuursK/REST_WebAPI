package enums;

public enum UserStatus {

    ACTIVE("active"),
    CREATED("created"),
    BLOCKED("blocked");

    private String message;

    UserStatus(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

