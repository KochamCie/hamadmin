package com.beelego.repository.primary.account;

import com.beelego.entity.primary.account.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
@Repository
public interface UserRepository extends JpaRepository<User, String> {

}
