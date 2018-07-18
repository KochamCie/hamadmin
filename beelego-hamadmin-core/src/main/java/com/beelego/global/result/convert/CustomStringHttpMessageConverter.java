package com.beelego.global.result.convert;

import com.beelego.global.result.ApiResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.Charset;

/**
 * @author : hama
 * @since : created in  2018/6/29
 */
@Slf4j
public class CustomStringHttpMessageConverter extends StringHttpMessageConverter {

    public CustomStringHttpMessageConverter(Charset charset) {
        super(charset);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        if (!aClass.equals(String.class)) {
            return false;
        }
        return true;
    }


    @Override
    protected void writeInternal(String s, HttpOutputMessage httpOutputMessage) throws IOException, HttpMessageNotWritableException {
        Charset charset = httpOutputMessage.getHeaders().getContentType().getCharset();
        httpOutputMessage.getHeaders().setContentLength(ApiResult.ok(s).toJson().getBytes().length);
        StreamUtils.copy(ApiResult.ok(s).toJson(), charset, httpOutputMessage.getBody());
    }

}
