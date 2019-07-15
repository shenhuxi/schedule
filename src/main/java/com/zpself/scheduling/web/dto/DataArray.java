package com.zpself.scheduling.web.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.Arrays;

@ApiModel(
        description = "通用数组类型,类型根据业务而定"
)
public class DataArray<T> {
    @ApiModelProperty(
            value = "数据",
            required = true,
            allowEmptyValue = true
    )
    T[] data;

    public T[] getData() {
        return this.data;
    }

    public void setData(T[] data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "DataArray(data=" + Arrays.deepToString(this.getData()) + ")";
    }

    public DataArray() {
    }

    public DataArray(T[] data) {
        this.data = data;
    }
}

