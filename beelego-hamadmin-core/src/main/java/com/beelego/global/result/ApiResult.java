package com.beelego.global.result;

import com.beelego.enums.DynamicEnum;
import com.beelego.enums.ErrorCodeEnum;
import com.beelego.global.base.BaseObject;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/26
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiResult<T> extends BaseObject {

    private T data;

    private String[] debug;

    private List<ApiError> error = null;

    public boolean isSuccess() {
        return null == this.error ? true : this.error.isEmpty();
    }

    public ApiResult addError(ErrorCodeEnum errorCodeEnum) {
        this.error = new ArrayList<>();
        this.error.add(new ApiError(errorCodeEnum));
        return this;
    }

    public ApiResult addError(String code, String msg) {
        this.error = new ArrayList<>();
        this.error.add(new ApiError(DynamicEnum.getEnum(ErrorCodeEnum.class, code, code, msg)));
        return this;
    }

    public void setData(T data) {
        this.data = data;
    }

    public List<ApiError> getError() {
        return error;
    }

    public T getData() {
        return data;
    }

    public String[] getDebug() {
        return debug;
    }

    public void setDebug(String[] debug) {
        this.debug = debug;
    }

    class ApiError extends BaseObject {

        @JsonIgnore
        private ErrorCodeEnum errorCodeEnum;


        public ApiError(ErrorCodeEnum errorCodeEnum) {
            this.errorCodeEnum = errorCodeEnum;
        }

        public String getCode() {
            return errorCodeEnum.getCode();
        }

        public String getMsg() {
            return errorCodeEnum.getMsg();
        }
    }
}
