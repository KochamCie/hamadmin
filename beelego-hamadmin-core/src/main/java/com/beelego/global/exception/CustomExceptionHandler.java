package com.beelego.global.exception;

import com.beelego.enums.ErrorCodeEnum;
import com.beelego.global.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author : hama
 * @since : created in  2018/6/28
 */
@Slf4j
@ControllerAdvice
public class CustomExceptionHandler {

    private ApiResult defaultMsg(Exception e) {
        e.printStackTrace();
        return ApiResult.ok(e.getMessage()).addError(ErrorCodeEnum.RUNTIME_EXCEPTION).addDebug(ExceptionUtils.getStackFrames(e));
    }

    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ApiResult exceptionHandler(Exception e) {
        if (e instanceof ApiException) {
            ApiException apiException = (ApiException) e;
            log.error("{}, {}", apiException.getCode(), apiException.getMsg());
            return ApiResult.ok().addError(apiException.getCode(), apiException.getMsg());
        }
        return defaultMsg(e);
    }

    // TODO: other exceptions handler


}
