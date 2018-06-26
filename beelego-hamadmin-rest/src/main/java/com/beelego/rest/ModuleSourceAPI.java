package com.beelego.rest;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.enums.ModuleTypeEnum;
import com.beelego.host.ModuleSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * @author : hama
 * @since : created in  2018/6/25
 */
@RestController
public class ModuleSourceAPI {


    @Autowired
    ModuleSourceService moduleSourceService;


    @PostMapping(value = "/moduleSource")
    public Object saveModuleSource() {
        ModuleSource moduleSource = new ModuleSource();
        moduleSource.setIp("172.06.0.69");
        moduleSource.setModule(ModuleTypeEnum.SBADMIN);
        moduleSource.setDescription(ModuleTypeEnum.SBADMIN.name());
        return moduleSourceService.save(moduleSource);
    }

}
