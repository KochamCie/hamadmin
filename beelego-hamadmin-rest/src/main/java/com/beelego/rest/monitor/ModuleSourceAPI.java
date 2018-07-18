package com.beelego.rest.monitor;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.global.base.BaseAPI;
import com.beelego.global.result.ApiResult;
import com.beelego.host.ModuleSourceService;
import com.beelego.payload.CreateSource;
import com.beelego.payload.Login;
import com.beelego.payload.Test;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Slf4j
@Api("模块管理")
@RestController
public class ModuleSourceAPI extends BaseAPI {

    @Autowired
    ModuleSourceService moduleSourceService;

    @ApiOperation(value = "模块管理")
    @RequestMapping(value = "/moduleSource", method = RequestMethod.POST)
    public Object saveModuleSource(
            @Valid @RequestBody @ApiParam(name = "source", value = "json格式", required = true) CreateSource source,
            BindingResult bindingResult) {
        ApiResult checks = checkBindingResult(bindingResult);
        if (!checks.isSuccess()) {
            return checks;
        }
        return moduleSourceService.save(source);
    }


    @ApiOperation(value = "修改moduleSource")
    @RequestMapping(value = "/moduleSource", method = RequestMethod.PUT)
    public Object updateModuleSource(@RequestBody @ApiParam(name = "moduleSource", value = "json格式", required = true) ModuleSource moduleSource) {
        return moduleSourceService.update(moduleSource);
    }


    @ApiOperation(value = "列表moduleSource")
    @RequestMapping(value = "/moduleSource", method = RequestMethod.GET)
    public Object deleteModuleSource(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
        log.info("pageNo is :{}, pageSize is :{}", pageNo, pageSize);
        return moduleSourceService.findAll(PageRequest.of(pageNo-1, pageSize));
    }

    @ApiOperation(value = "删除moduleSource")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNo", value = "页码",  required = true),
            @ApiImplicitParam(name = "pageSize", value = "当前页的数量",  required = true)
    })
    @RequestMapping(value = "/moduleSource/{id}", method = RequestMethod.DELETE)
    public Object deleteModuleSource(@PathVariable("id") String id) {
        return moduleSourceService.delete(id);
    }


    @ApiOperation(value = "login")
    @RequestMapping(value = "/login/login", method = RequestMethod.POST)
    public Object login(@RequestBody Login login) {
        log.info("111 is {},{}", login);
        Test test = new Test();
        test.setToken(System.currentTimeMillis() + "");
        test.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        test.setIntroduction("我是超级管理员");
        test.setName("Super Admin");
        String[] roles = {"admin"};
        test.setRoles(roles);
        return ApiResult.ok(test);
    }

    @ApiOperation(value = "info")
    @RequestMapping(value = "/user/info", method = RequestMethod.GET)
    public Object info() {
        Test test = new Test();
        test.setToken(System.currentTimeMillis() + "");
        test.setAvatar("https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        test.setIntroduction("我是超级管理员");
        test.setName("Super Admin");
        String[] roles = {"admin"};
        test.setRoles(roles);
        return ApiResult.ok(test);
    }

}
