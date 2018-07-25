package com.beelego.ds;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

/**
 * @author : hama
 * @since : created in  2018/7/25
 */
@Configuration
public class CustomAuditorAware implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.ofNullable("666"+"^^"+System.currentTimeMillis());
    }
}