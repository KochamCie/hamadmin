package com.beelego.enums;

import com.beelego.global.base.BaseEnum;

/**
 * 方便配置路由
 * 路由模块
 * @author : hama
 * @since : created in  2018/6/25
 */
public enum ModuleTypeEnum implements BaseEnum {

    SBADMIN,
    DRUID,
    EUREKA,
    SWAGGER,
    DISCONF,
    APOLLO,
    SPLUNK,
    SQLPAD,
    ELK,
    QUARTZ,
    SLEUTH;

    @Override
    public String getKey() {
        return null;
    }
}
