package io.github.daviszhao.stonemason.busEvent.services.handlers;

import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.services.consumers.AskEventConsumer;

import javax.inject.Inject;

public abstract class AskEventHandler extends AbstractEventHandler<AskEvent, AskEventHandler> {
    @Inject
    private AskEventConsumer consumer;

    @Override
    protected AskEventConsumer getConsumer() {
        return consumer;
    }
}
