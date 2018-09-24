package com.beelego.repository.primary.account;

import com.beelego.entity.primary.account.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
@Repository
public interface UserRepository extends JpaRepository<Users, String> {


    @Query(value = "SELECT u.* FROM user u left join module_source ms" +
            " on u.name=ms.description WHERE u.name in (:name)",
            nativeQuery = true)
    List<Users> findUserByStatusAndNameNamedParamsNative(@Param("name") List<String> name);

}
