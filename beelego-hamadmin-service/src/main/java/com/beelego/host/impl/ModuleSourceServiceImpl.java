package com.beelego.host.impl;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.host.ModuleSourceService;
import com.beelego.repository.primary.ModuleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Service("moduleSourceService")
public class ModuleSourceServiceImpl implements ModuleSourceService {
    @Autowired
    ModuleSourceRepository moduleSourceRepository;

    @Override
    public ModuleSource save(ModuleSource moduleSource) {
        return moduleSourceRepository.saveAndFlush(moduleSource);
    }

    @Override
    public Page<ModuleSource> findAll(Pageable pageable) {
        return moduleSourceRepository.findAll(pageable);
    }


}
