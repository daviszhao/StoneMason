package io.github.daviszhao.stonemason.busEvent.payloads;

import java.io.Serializable;
import java.util.HashMap;

public class EventPayload extends HashMap<String, Object> {
    public EventPayload with(String key, Serializable value) {
        put(key, value);
        return this;
    }
}
