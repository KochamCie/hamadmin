package com.beelego.user;


import com.beelego.entity.primary.account.Users;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
public interface UserService {

    public Users saveUser(Users user);

    List<Users> listUser();

    Users findById(String id);
}
