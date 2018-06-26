package com.beelego.global;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpResponse;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * 自定义HttpMessageConverter
 * 全局设置返回json格式
 *
 * @author : hama
 * @since : created in  2018/6/25
 */
@Slf4j
public class CustomHttpMessageConverter extends MappingJackson2HttpMessageConverter {

    @Override
    public void setObjectMapper(ObjectMapper objectMapper) {
        super.setObjectMapper(objectMapper);
        getObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);
        getObjectMapper().configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
    }

    @Override
    protected boolean supports(Class<?> clazz) {
        return super.supports(clazz);
    }

    @Override
    public void setSupportedMediaTypes(List<MediaType> supportedMediaTypes) {
        super.setSupportedMediaTypes(supportedMediaTypes);
    }

    /**
     *
     *
     * @param object
     * @param type
     * @param outputMessage
     * @throws IOException
     * @throws HttpMessageNotWritableException
     */
    @Override
    protected void writeInternal(Object object, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        // 判断来源
        ServletServerHttpResponse httpOutputMessage = (ServletServerHttpResponse) outputMessage;
        HttpServletResponse rs = httpOutputMessage.getServletResponse();
        super.writeInternal(object, type, outputMessage);
    }

}
