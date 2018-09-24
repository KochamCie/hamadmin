package com.beelego.user;

import com.beelego.entity.primary.account.Users;
import com.beelego.repository.primary.account.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Users saveUser(Users user) {
        return userRepository.save(user);
    }

    @Override
    public List<Users> listUser() {
        List<Users> users = userRepository.findAll();
        List<String> us=new ArrayList<String>(){{
           add("熊大1532513618310") ;
           add("熊二1532513755995") ;
        }};
        users.addAll(0, userRepository.findUserByStatusAndNameNamedParamsNative(us));
        return users;
    }

    @Override
    public Users findById(String id) {
        return userRepository.findById(id).get();
    }
}
