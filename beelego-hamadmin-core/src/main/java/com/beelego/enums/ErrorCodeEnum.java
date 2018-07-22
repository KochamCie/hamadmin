package com.beelego.enums;

import com.alibaba.fastjson.JSON;
import com.beelego.global.base.BaseEnum;
import lombok.ToString;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;


/**
 * @author : hama
 * @since : created in  2018/6/28
 */
@ToString
public enum ErrorCodeEnum implements BaseEnum {

    //    SUCCESS("0", "操作成功！"),
    RUNTIME_EXCEPTION("1", "系统运行异常！"),
    PARAMETERS_FIELD_ERROR("2","请求参数约束校验不通过！"),
    ARGS_BEYOND_CONSTRUCTOR("600001", "参数数量超出构造函数参数数量！"),
    WANTED_NOT_FOUND("404001", "资源不存在！"),
    FAILED("666001", "操作失败！"),
    RESOURCE_ALREADY_EXIST("666002", "资源已存在！");

    private String code;

    private String msg;

    ErrorCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String getKey() {
        return this.code;
    }

    public String toJson(){
        // return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, false);
        return JSON.toJSONString(this);
    }

    public String toJson2(){
        return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, false);
    }
}
