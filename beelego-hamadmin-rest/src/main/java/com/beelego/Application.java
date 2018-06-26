package com.beelego;

import com.beelego.global.CustomHttpMessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;

import java.nio.charset.Charset;

@SpringBootApplication
public class Application {

    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        CustomHttpMessageConverter converter = new CustomHttpMessageConverter();
        converter.setDefaultCharset(Charset.forName("UTF-8"));
        return new HttpMessageConverters(converter);
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
