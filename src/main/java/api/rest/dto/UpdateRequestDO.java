package api.rest.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Objects;

@ApiModel
public class UpdateRequestDO extends DtoBase {

    @ApiModelProperty(value = "password", required = true)
    private String password;

    @ApiModelProperty(value = "status", required = true)
    private String status;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UpdateRequestDO password(String password) {
        this.password = password;
        return this;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public UpdateRequestDO status(String status) {
        this.status = status;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (! super.equals(o)) return false;
        final UpdateRequestDO that = (UpdateRequestDO) o;
        return  Objects.equals(password, that.password)&
                Objects.equals(status, that.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, status);
    }

    @Override
    protected StringBuilder fieldsToString() {
        final StringBuilder sb = super.fieldsToString();
        appendField(sb, "password", password);
        appendField(sb, "status", status);
        return sb;
    }

}