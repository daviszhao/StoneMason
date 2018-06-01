package io.github.daviszhao.stonemason.db.base.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.daviszhao.stonemason.utils.SpringContextHolder;
import org.jooq.impl.AbstractConverter;

import java.io.IOException;
import java.io.Serializable;

public class JsonObjectsConverter<INFO extends Serializable> extends AbstractConverter<String, INFO> {
    private static ObjectMapper objectMapper;
    private final Class<INFO> targetClass;

    public JsonObjectsConverter(Class<INFO> infoClass) {
        super(String.class, infoClass);
        this.targetClass = infoClass;
    }

    private static ObjectMapper getObjectMapper() {
        return SpringContextHolder.getBean(ObjectMapper.class);
    }

    @Override
    public INFO from(String databaseObject) {
        try {
            return getObjectMapper().readValue(databaseObject, targetClass);
        } catch (IOException e) {

            throw new RuntimeException("数据转换错误,不是正常的JSON格式", e);
        }
    }

    @Override
    public String to(INFO userObject) {
        try {
            return getObjectMapper().writeValueAsString(userObject);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("数据转换错误,不能转换成JSON格式", e);
        }
    }

}
