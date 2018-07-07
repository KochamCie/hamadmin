package com.beelego.rest;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.enums.ErrorCodeEnum;
import com.beelego.enums.ModuleTypeEnum;
import com.beelego.global.base.BaseAPI;
import com.beelego.global.exception.ApiException;
import com.beelego.global.result.ApiResult;
import com.beelego.global.result.convert.NoConvertMessage;
import com.beelego.host.ModuleSourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Api("模块管理")
@RestController
public class ModuleSourceAPI extends BaseAPI {


    @Autowired
    ModuleSourceService moduleSourceService;

    @ApiOperation(value = "模块管理")
    @RequestMapping(value = "/moduleSource", method = RequestMethod.POST)
    public Object saveModuleSource() {
        ModuleSource moduleSource = new ModuleSource();
        moduleSource.setIp("172.06.0.69");
        moduleSource.setModule(ModuleTypeEnum.SBADMIN);
        moduleSource.setDescription(ModuleTypeEnum.SBADMIN.name() + "阿斯蒂芬");
        return moduleSourceService.save(moduleSource);
    }

    @ApiOperation(value = "模块列表")
    @RequestMapping(value = "/moduleSource", method = RequestMethod.GET)
    public Object getModuleSource() {
        return moduleSourceService.findAll(new PageRequest(0, 100));
    }


    @ApiOperation(value = "模块列表2")
    @RequestMapping(value = "/moduleSource2", method = RequestMethod.GET)
    public Object getModuleSource2() {
        return new ApiResult<>().addError(ErrorCodeEnum.FAILED);
    }

    @ApiOperation(value = "模块列表3")
    @RequestMapping(value = "/moduleSource3", method = RequestMethod.GET)
    public Object getModuleSource3() {
        return new ApiResult<>().addError("99999", "撒打发发顺丰的");
    }

    @ApiOperation(value = "模块列表4")
    @RequestMapping(value = "/moduleSource4", method = RequestMethod.GET)
    public Object getModuleSource4() {
        int a = 1/0;
        return new ApiResult<>().addError("99999", "撒打发发顺丰的");
    }

    @Autowired
    NoConvertMessage noConvertMessage;

    @ApiOperation(value = "模块列表5")
    @RequestMapping(value = "/moduleSource5", method = RequestMethod.GET)
    public Object getModuleSource5() {
        throw new ApiException(ErrorCodeEnum.FAILED);
    }
    @ApiOperation(value = "模块列表6")
    @RequestMapping(value = "/moduleSource6", method = RequestMethod.GET)
    public Object getModuleSource6() {
        return noConvertMessage.getIgnore();
    }
    @ApiOperation(value = "模块列表7")
    @RequestMapping(value = "/moduleSource7", method = RequestMethod.GET)
    public Object getModuleSource7() {
        return "view说";
    }

    @ApiOperation(value = "模块列表8")
    @RequestMapping(value = "/moduleSource8", method = RequestMethod.GET)
    public Object getModuleSource8() {
        return Arrays.stream(ErrorCodeEnum.values()).collect(Collectors.toMap(ErrorCodeEnum::getCode, ErrorCodeEnum::getMsg));
    }
}
