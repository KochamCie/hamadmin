package com.beelego.enums;

import com.beelego.global.base.BaseEnum;

/**
 * 方便配置路由
 *
 * @author : hama
 * @since : created in  2018/6/25
 */
public enum ModuleTypeEnum implements BaseEnum {

    SBADMIN,
    DRUID,
    EUREKA;

    @Override
    public String getKey() {
        return null;
    }
}
