package com.beelego.payload;

import com.beelego.global.base.BaseObject;
import lombok.Data;

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

}
