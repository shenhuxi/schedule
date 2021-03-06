package com.zpself.scheduling.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(
        description = "通用数据类型,类型根据业务而定"
)
public class SingleData<T> {
    @ApiModelProperty(
            value = "数据",
            required = true,
            allowEmptyValue = true
    )
    T data;

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "SingleData(data=" + this.getData() + ")";
    }

    public SingleData() {
    }

    public SingleData(T data) {
        this.data = data;
    }
}
