package com.beelego.global.base;

import com.beelego.enums.ErrorCodeEnum;
import com.beelego.global.result.ApiResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/26
 */
public class BaseAPI {

    public ApiResult checkBindingResult(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errors = new ArrayList<String>();
            bindingResult.getAllErrors().stream().forEach(error -> {
                FieldError fieldError = (FieldError) error;
                //错误中输出字段名字
                String s = fieldError.getField() + ":" + error.getDefaultMessage();
                //输出检验错误的字段的名字
                System.out.println(s);
                errors.add(s);
            });
            return ApiResult.ok(String.join("||", errors)).addError(ErrorCodeEnum.PARAMETERS_FIELD_ERROR);
        }
        return ApiResult.ok();
    }
}
