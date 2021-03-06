package com.beelego.host.impl;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.enums.ErrorCodeEnum;
import com.beelego.global.result.ApiResult;
import com.beelego.host.ModuleSourceService;
import com.beelego.payload.CreateSource;
import com.beelego.repository.primary.ModuleSourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@Service("moduleSourceService")
public class ModuleSourceServiceImpl implements ModuleSourceService {
    @Autowired
    ModuleSourceRepository moduleSourceRepository;

    @Override
    public ApiResult save(CreateSource source) {

        if(null != moduleSourceRepository.findByIpAndPort(source.getIp(), source.getPort())){
            return ApiResult.ok().addError(ErrorCodeEnum.RESOURCE_ALREADY_EXIST);
        }
        ModuleSource moduleSource = new ModuleSource();
        moduleSource.setCreateTime(new Date());
        moduleSource.setDescription(source.getDescription());
        moduleSource.setModule(source.getModule());
        moduleSource.setIp(source.getIp());
        moduleSource.setPort(source.getPort());
        moduleSource.setUrl(source.getUrl());
        return ApiResult.ok(moduleSourceRepository.saveAndFlush(moduleSource));
    }

    @Override
    public Page<ModuleSource> findAll(Pageable pageable) {
        return moduleSourceRepository.findAll(pageable);
    }

    public boolean delete(String id) {
        moduleSourceRepository.deleteById(id);
        return true;
    }

    @Override
    public ModuleSource update(String id, CreateSource source) {
        Optional<ModuleSource> oModuleSource = moduleSourceRepository.findById(id);
        if (oModuleSource.isPresent()) {
            ModuleSource moduleSource = oModuleSource.get();
            moduleSource.setCreateTime(new Date());
            moduleSource.setDescription(source.getDescription());
            moduleSource.setModule(source.getModule());
            moduleSource.setIp(source.getIp());
            moduleSource.setPort(source.getPort());
            moduleSource.setUrl(source.getUrl());
            return moduleSourceRepository.saveAndFlush(moduleSource);
        }
        return null;
    }

    @Override
    public List<ModuleSource> findRouter() {
        return moduleSourceRepository.findAllByPublish(true,
                Sort.by(new Sort.Order(Sort.Direction.ASC, "module"), new Sort.Order(Sort.Direction.ASC, "publishTime")));
    }

    @Override
    public boolean publishModuleSource(String id) {
        Optional<ModuleSource> oModuleSource = moduleSourceRepository.findById(id);
        if (oModuleSource.isPresent()) {
            ModuleSource source = oModuleSource.get();
            source.setPublish(!source.isPublish());
            source.setPublishTime(new Date());
            moduleSourceRepository.saveAndFlush(source);
            return true;
        }
        return false;
    }
}
