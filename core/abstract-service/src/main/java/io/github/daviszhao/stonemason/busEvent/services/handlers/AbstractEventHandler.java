package io.github.daviszhao.stonemason.busEvent.services.handlers;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.services.consumers.EventConsumer;
import org.springframework.beans.factory.InitializingBean;

import javax.inject.Inject;

public abstract class AbstractEventHandler<E extends AbstractBusEvent, H extends AbstractEventHandler<E, H>> implements InitializingBean {
    @Inject
    private EventConsumer<E, H> consumer;

    protected EventConsumer<E, H> getConsumer() {
        return consumer;
    }

    ;

    @Override
    public void afterPropertiesSet() {
        getConsumer().registerHandler(getInstance());
    }

    protected abstract H getInstance();

    public abstract String getType();

    public abstract void handle(String payload);
}
