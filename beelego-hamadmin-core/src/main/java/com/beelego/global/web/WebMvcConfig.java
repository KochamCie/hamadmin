package com.beelego.global.web;

import com.beelego.global.filter.RestfulFilter;
import com.beelego.global.result.convert.CustomHttpMessageConverter;
import com.beelego.global.result.convert.CustomStringHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * @author : hama
 * @since : created in  2018/6/26
 */
@Configuration
public class WebMvcConfig {

    @Bean
    public FilterRegistrationBean getDemoFilter() {
        RestfulFilter restfulFilter = new RestfulFilter();
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(restfulFilter);
        List<String> urlPatterns = new ArrayList<String>();
        urlPatterns.add("/*");
        registrationBean.setUrlPatterns(urlPatterns);
        registrationBean.setOrder(1);
        return registrationBean;
    }

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        CustomHttpMessageConverter converter = new CustomHttpMessageConverter(Charset.forName("UTF-8"));
        CustomStringHttpMessageConverter stringConverter = new CustomStringHttpMessageConverter(Charset.forName("UTF-8"));
        return new HttpMessageConverters(converter, stringConverter);
    }


}