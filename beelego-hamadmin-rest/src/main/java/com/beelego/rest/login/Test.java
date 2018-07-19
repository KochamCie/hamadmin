package com.beelego.rest.login;

import com.beelego.entity.primary.ModuleSource;
import com.beelego.global.base.BaseObject;
import lombok.Data;

import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/7/12
 */
@Data
public class Test extends BaseObject {

    private String [] roles;

    private String token;

    private String introduction;

    private String avatar;

    private String name;

    private List<ModuleSource> sources;

}
