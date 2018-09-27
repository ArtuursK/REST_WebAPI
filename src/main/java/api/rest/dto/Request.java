package api.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel
public class Request extends DtoBase {

    @ApiModelProperty(value = "userName", required = true)
    private String username;

    @ApiModelProperty(value = "password", required = true)
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Request username(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Request password(String password) {
        this.password = password;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (! super.equals(o)) return false;
        final Request that = (Request) o;
        return  Objects.equals(username, that.username)&
                Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password);
    }

    @Override
    protected StringBuilder fieldsToString() {
        final StringBuilder sb = super.fieldsToString();
        appendField(sb, "username", username);
        appendField(sb, "password", password);
        return sb;
    }

}