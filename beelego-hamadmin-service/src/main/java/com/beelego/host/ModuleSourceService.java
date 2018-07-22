package com.beelego.host;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.global.result.ApiResult;
import com.beelego.payload.CreateSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */

public interface ModuleSourceService {

    ApiResult save(CreateSource source);

    Page<ModuleSource> findAll(Pageable pageable);

    boolean delete(String id);

    ModuleSource update(String id, CreateSource source);

    List<ModuleSource> findRouter();

    boolean publishModuleSource(String id);
}
