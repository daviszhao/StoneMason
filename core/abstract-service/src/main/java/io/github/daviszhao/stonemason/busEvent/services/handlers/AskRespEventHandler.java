package io.github.daviszhao.stonemason.busEvent.services.handlers;

import io.github.daviszhao.stonemason.busEvent.event.AskResponseEvent;
import io.github.daviszhao.stonemason.busEvent.services.consumers.AskRespEventConsumer;

import javax.inject.Inject;

public abstract class AskRespEventHandler extends AbstractEventHandler<AskResponseEvent, AskRespEventHandler> {
    @Inject
    private AskRespEventConsumer consumer;

    @Override
    protected AskRespEventConsumer getConsumer() {
        return consumer;
    }
}
