package com.beelego.host;

import com.beelego.entity.primary.ModuleSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */

public interface ModuleSourceService {

    ModuleSource save(ModuleSource moduleSource);

    Page<ModuleSource> findAll(Pageable pageable);
}
