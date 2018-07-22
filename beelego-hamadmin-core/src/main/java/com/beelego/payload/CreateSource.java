package com.beelego.payload;

import com.beelego.enums.ModuleTypeEnum;
import com.beelego.global.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author : hama
 * @since : created in  2018/7/18
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSource extends BaseObject {

    @NotNull(message = "ip不能为空！")
    private String ip;

    @NotNull(message = "port不能为空！")
    private int port;

    @NotNull(message = "module不能为空！")
    private ModuleTypeEnum module;

    @NotNull(message = "description不能为空！")
    private String description;

    @NotNull(message = "url不能为空！")
    private String url;
}
