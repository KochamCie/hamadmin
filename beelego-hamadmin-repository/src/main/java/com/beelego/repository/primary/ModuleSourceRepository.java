package com.beelego.repository.primary;

import com.beelego.entity.primary.ModuleSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Repository
public interface ModuleSourceRepository extends JpaRepository<ModuleSource, String> {

    @Override
    Page<ModuleSource> findAll(Pageable pageable);

    List<ModuleSource> findAllByPublish(@Param("publish") boolean publish, Sort sort);
}
