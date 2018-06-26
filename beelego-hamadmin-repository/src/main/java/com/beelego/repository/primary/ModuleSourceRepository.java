package com.beelego.repository.primary;

import com.beelego.entity.primary.ModuleSource;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Repository
public interface ModuleSourceRepository extends JpaRepository<ModuleSource, String> {

}
