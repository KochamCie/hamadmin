package com.beelego.global.base;

import com.alibaba.fastjson.JSON;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author : hama
 * @since : created in  2018/6/26
 */
public abstract class BaseObject implements Serializable {

    private static final long serialVersionUID = 20180626666666L;

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(this, ToStringStyle.MULTI_LINE_STYLE, false);
    }

    public String toJson() {
        // return ReflectionToStringBuilder.toString(this, ToStringStyle.JSON_STYLE, false);
        return JSON.toJSONString(this);
    }

}