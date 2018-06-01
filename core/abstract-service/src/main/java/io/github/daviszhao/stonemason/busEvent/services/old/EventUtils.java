package io.github.daviszhao.stonemason.busEvent.services.old;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.AskEventStatus;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureReason;
import io.github.daviszhao.stonemason.exceptions.event.EventException;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.io.IOException;

public class EventUtils implements ApplicationContextAware {

    public static final String SUCCESS_CALLBACK_NAME = "onSuccess";

    public static final String FAILED_CALLBACK_NAME = "onFailure";
    private static ObjectMapper objectMapper;

    /*   *//**
     * 将事件转成json
     * @param event
     * @return
     *//*
    public static String serializeEvent(BaseEvent event) {
        return JsonUtils.object2Json(event);
    }

    */

    /**
     * 将json转成事件对象
     *
     * @param data
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T extends AbstractBusEvent> T deserializeEvent(String data, Class<T> clazz) {
        try {
            return objectMapper.readValue(data, clazz);
        } catch (IOException e) {

            throw new RuntimeException("数据转换错误,不是正常的JSON格式", e);
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
*/
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
        objectMapper = applicationContext.getBean(ObjectMapper.class)
                .configure(JsonParser.Feature.IGNORE_UNDEFINED, true)
                .configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false)
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }
}