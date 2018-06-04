package io.github.daviszhao.stonemason.busEvent.services.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import javax.inject.Named;
import java.io.IOException;

@Named
@AllArgsConstructor
public class EventUtils {

    public static final String SUCCESS_CALLBACK_NAME = "onSuccess";

    public static final String FAILED_CALLBACK_NAME = "onFailure";
    private ObjectMapper objectMapper;


    /**
     * 将json转成事件对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public <T> T deserialize(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {

            throw new RuntimeException("数据转换错误,不是正常的JSON格式", e);
        }
    }

    public <T> T deserialize(String data, final TypeReference<T> typeReference) {
        try {
            return objectMapper.readValue(data, typeReference);
        } catch (IOException e) {

            throw new RuntimeException("数据转换错误,不是正常的JSON格式", e);
        }
    }

    public String serialize(Object payload) {
        try {
            return objectMapper.writeValueAsString(payload);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("数据转换错误,不能转成JSON格式", e);
        }
    }


    /**
     * @param payload
     * @return
     *//*
    @SuppressWarnings("unchecked")
    public static Map<String, Object> retrieveEventMapFromJson(String payload) {
        //这里没有event的子类信息, 所以只能用map代替
        Map<String, Object> map = JsonUtils.json2Object(payload, Map.class);
        String type = (String)map.get("type");
        if(StringUtils.isBlank(type)) {
            throw new EventException(String.format("event type is blank, payload: %s", payload));
        }
        if(EventType.valueOfIgnoreCase(type) == null) {
            throw new EventException(String.format("unknown event type:%s, payload: %s", type, payload));
        }
        if(map.get("id") == null) {
            throw new EventException(String.format("event id is blank, payload: %s", payload));
        }

        return map;

    }


    public static String getAskCallbackMethodName(boolean success) {
        return success ? SUCCESS_CALLBACK_NAME : FAILED_CALLBACK_NAME;
    }

    public static FailureReason fromAskEventStatus(AskEventStatus status) {

        switch (status) {
            case CANCELLED:
                return FailureReason.CANCELLED;
            case FAILED:
                return FailureReason.FAILED;
            case TIMEOUT:
                return FailureReason.TIMEOUT;
            default:
                throw new EventException("unknown FailureReason from AskEventStatus: " + status);
        }

    }

    public static AskEventStatus fromFailureReason(FailureReason reason) {

        switch (reason) {
            case CANCELLED:
                return AskEventStatus.CANCELLED;
            case FAILED:
                return AskEventStatus.FAILED;
            case TIMEOUT:
                return AskEventStatus.TIMEOUT;
            default:
                throw new EventException("unknown AskEventStatus from FailureReason: " + reason);
        }

    }


    public static <T extends AbstractBusEvent> String serializeEvent(T event, Class<T> clazz) {
        try {
            return objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("数据转换错误,不能转换成JSON格式", e);
        }
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        objectMapper = applicationContext.getBean(ObjectMapper.class)/*
                .configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)*/;

}