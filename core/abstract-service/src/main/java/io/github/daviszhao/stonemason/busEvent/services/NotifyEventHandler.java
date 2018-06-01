package io.github.daviszhao.stonemason.busEvent.services;

import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;

public abstract class NotifyEventHandler implements InitializingBean {
    @Inject
    private EventConsumer consumer;


    @Override
    public void afterPropertiesSet() {
        consumer.registerHandler(this);
    }

    protected abstract String getType();

    public abstract void handle(EventPayload payload);
}
