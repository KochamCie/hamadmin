package com.beelego.user;

import com.beelego.entity.primary.account.User;
import com.beelego.repository.primary.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> listUser() {
        List<User> users = userRepository.findAll();
        return users;
    }

    @Override
    public User findById(String id) {
        return userRepository.findById(id).get();
    }
}
