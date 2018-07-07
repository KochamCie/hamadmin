package com.beelego.global.result.convert;

import com.beelego.global.base.BaseObject;
import com.beelego.global.util.YamlPropertyLoaderFactory;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/28
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-noconvert.yml", factory = YamlPropertyLoaderFactory.class)
@ConfigurationProperties(prefix = "convert")
public class NoConvertMessage extends BaseObject {
    private List<String> ignore = new ArrayList<String>();
}
