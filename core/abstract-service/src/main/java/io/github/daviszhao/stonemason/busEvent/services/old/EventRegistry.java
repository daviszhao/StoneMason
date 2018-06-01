package io.github.daviszhao.stonemason.busEvent.services.old;

import javax.inject.Named;

@Named
public class EventRegistry {
    public static AskEventCallback getAskEventCallback(String callbackClassName) {
        return null;
    }

    public boolean isEventRevokable(String eventtype) {
        return false;
    }
}
