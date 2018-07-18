package com.beelego.payload;

import com.beelego.global.base.BaseObject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author : hama
 * @since : created in  2018/7/12
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Login extends BaseObject {

    private String username;

    private String password;

}
