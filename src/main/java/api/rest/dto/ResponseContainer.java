package api.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel
public class ResponseContainer extends DtoBase {

    @ApiModelProperty("UserName")
    private String username;

    @ApiModelProperty("Timestamp")
    private String timestamp;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ResponseContainer username(String username) {
        this.username = username;
        return this;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public ResponseContainer timestamp(String timestamp) {
        this.timestamp = timestamp;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (! super.equals(o)) return false;
        final ResponseContainer that = (ResponseContainer) o;
        return  Objects.equals(username, that.username) &&
                Objects.equals(timestamp, that.timestamp);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, timestamp);
    }

    @Override
    protected StringBuilder fieldsToString() {
        final StringBuilder sb = super.fieldsToString();
        appendField(sb, "username", username);
        appendField(sb, "timestamp", timestamp);
        return sb;
    }

}
