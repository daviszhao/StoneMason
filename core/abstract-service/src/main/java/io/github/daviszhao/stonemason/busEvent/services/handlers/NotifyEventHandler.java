package io.github.daviszhao.stonemason.busEvent.services.handlers;

import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.services.consumers.NotifyEventConsumer;

import javax.inject.Inject;

public abstract class NotifyEventHandler extends AbstractEventHandler<NotifyEvent, NotifyEventHandler> {

    @Inject
    private NotifyEventConsumer consumer;

    @Override
    protected NotifyEventConsumer getConsumer() {
        return consumer;
    }
}
