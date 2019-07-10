package com.zpself.scheduling.handler;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "通用API出错时，返回的错误信息数据"
)
public class CommonError extends Throwable {
    @ApiModelProperty(
            value = "错误信息",
            required = true
    )
    private String message;
    @ApiModelProperty(
            value = "异常堆栈信息",
            required = false
    )
    private String exception;

    public CommonError(String message) {
        this.message = message;
    }

    public CommonError(String message, String exception) {
        this.message = message;
        this.exception = exception;
    }
    @Override
    public String getMessage() {
        return this.message;
    }

    private String getException() {
        return this.exception;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    @Override
    public String toString() {
        return "CommonError(message=" + this.getMessage() + ", exception=" + this.getException() + ")";
    }
}

