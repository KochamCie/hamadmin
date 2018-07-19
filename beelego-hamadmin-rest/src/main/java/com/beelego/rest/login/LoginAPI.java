package com.beelego.rest.login;

import com.beelego.global.base.BaseAPI;
import com.beelego.global.result.ApiResult;
import com.beelego.host.ModuleSourceService;
import com.beelego.payload.Login;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Slf4j
@Api("登陆")
@RestController
public class LoginAPI extends BaseAPI {

    @Autowired
    ModuleSourceService moduleSourceService;


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
        test.setSources(moduleSourceService.findRouter());
        return ApiResult.ok(test);
    }

}
