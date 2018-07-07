package com.beelego.global.exception;

import com.beelego.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * @author : hama
 * @since : created in  2018/6/28
 */
@Data
public class ApiException extends RuntimeException {

    private String code;

    private String msg;

    public ApiException(ErrorCodeEnum errorCodeEnum) {
        this.code = errorCodeEnum.getCode();
        this.msg = errorCodeEnum.getMsg();
    }

}
