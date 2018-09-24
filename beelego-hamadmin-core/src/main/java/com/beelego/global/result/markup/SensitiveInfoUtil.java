package com.beelego.global.result.markup;

import org.apache.commons.lang3.StringUtils;

/**
 * @author : hama
 * @since : created in  2018/7/2
 */

public class SensitiveInfoUtil {

    public static String nameMarkup(final String fullName) {
        if (StringUtils.isBlank(fullName)) {
            return "";
        }
        final String name = StringUtils.left(fullName, 1);
        return StringUtils.rightPad(name, StringUtils.length(fullName), "*");
    }
}
