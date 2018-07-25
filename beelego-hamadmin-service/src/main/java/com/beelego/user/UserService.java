package com.beelego.user;


import com.beelego.entity.primary.account.User;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
public interface UserService {

    public User saveUser(User user);

    List<User> listUser();

    User findById(String id);
}
