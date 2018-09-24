package com.beelego.rest.user;

import com.beelego.entity.primary.account.Users;
import com.beelego.global.base.BaseAPI;
import com.beelego.user.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
@Api("用户类API")
@RestController
@RequestMapping(value = "/user")
public class UserAPI extends BaseAPI {

    @Autowired
    UserService userService;

    @ApiOperation("添加用户信息")
    @RequestMapping(method = RequestMethod.POST)
    public Object saveUser() {
        Users user = new Users();
        user.setName("熊大" + System.currentTimeMillis());
        return userService.saveUser(user);
    }

    @ApiOperation("用户信息列表")
    @RequestMapping(method = RequestMethod.GET)
    public Object listUser() {
        List<Users> users = userService.listUser();
        for (Users user : users) {
            System.out.println(user.toJson());
        }
        return users;
    }


    @ApiOperation("用户信息列表")
    @RequestMapping(method = RequestMethod.PUT)
    public Object updateUser() {
        Users user = userService.findById("E66EF547-4DEA-4A58-B3E0-8A7625EB5F7A");
        user.setName("熊二" + System.currentTimeMillis());
        return userService.saveUser(user);
    }


    @ApiOperation("listUserTest")
    @RequestMapping(method = RequestMethod.GET, value = "/listUserTest")
    public Object listUserTest() {
        List<Users> users = userService.listUser();
        for (Users user : users) {
            System.out.println(user.toJson());
        }
        return users;
    }


}
