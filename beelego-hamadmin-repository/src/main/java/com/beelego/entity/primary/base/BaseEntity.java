package com.beelego.entity.primary.base;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
public abstract class BaseEntity implements Serializable {

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
