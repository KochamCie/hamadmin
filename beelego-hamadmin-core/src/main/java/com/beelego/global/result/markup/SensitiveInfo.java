package com.beelego.global.result.markup;

import com.beelego.enums.SensitiveTypeEnum;
import com.fasterxml.jackson.annotation.JacksonAnnotationsInside;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author : hama
 * @since : created in  2018/7/2
 */
@Retention(RetentionPolicy.RUNTIME)
@JacksonAnnotationsInside
@JsonSerialize(using = SensitiveInfoSerializer.class)
public @interface SensitiveInfo {

    /**
     * 敏感信息类型
     *
     * @return
     */
    SensitiveTypeEnum type() default SensitiveTypeEnum.NAME;
}
