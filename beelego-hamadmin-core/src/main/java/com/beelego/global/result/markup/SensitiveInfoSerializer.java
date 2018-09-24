package com.beelego.global.result.markup;

import com.beelego.enums.SensitiveTypeEnum;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.ContextualSerializer;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Objects;

/**
 * @author : hama
 * @since : created in  2018/7/2
 */
@Slf4j
@NoArgsConstructor
public class SensitiveInfoSerializer extends JsonSerializer<String> implements ContextualSerializer {

    private SensitiveTypeEnum type;

    public SensitiveInfoSerializer(final SensitiveTypeEnum type) {
        this.type = type;
    }

    @Override
    public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
        switch (this.type) {
            case MOBILE: {
                jsonGenerator.writeString(SensitiveInfoUtil.nameMarkup(s));
                break;
            }
            case ID_NUMBER: {
                jsonGenerator.writeString(SensitiveInfoUtil.nameMarkup(s));
                break;
            }
            case BANK_CARD: {
                jsonGenerator.writeString(SensitiveInfoUtil.nameMarkup(s));
                break;
            }
            case NAME: {
                jsonGenerator.writeString(SensitiveInfoUtil.nameMarkup(s));
                break;
            }
        }

    }

    @Override
    public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty) throws JsonMappingException {
        // target is String.class decorated by SensitiveInfo
        if (beanProperty != null) {
            if (Objects.equals(beanProperty.getType().getRawClass(), String.class)) {
                SensitiveInfo sensitiveInfo = beanProperty.getAnnotation(SensitiveInfo.class);
                if (sensitiveInfo == null) {
                    sensitiveInfo = beanProperty.getContextAnnotation(SensitiveInfo.class);
                }
                if (sensitiveInfo != null) {
                    return new SensitiveInfoSerializer(sensitiveInfo.type());
                }
            }
            return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
        }
        return serializerProvider.findNullValueSerializer(beanProperty);
    }
}

