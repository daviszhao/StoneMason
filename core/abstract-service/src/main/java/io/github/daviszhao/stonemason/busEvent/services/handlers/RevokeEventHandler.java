package io.github.daviszhao.stonemason.busEvent.services.handlers;

import io.github.daviszhao.stonemason.busEvent.event.RevokeAskEvent;
import io.github.daviszhao.stonemason.busEvent.services.consumers.RevokeEventConsumer;

import javax.inject.Inject;

public abstract class RevokeEventHandler extends AbstractEventHandler<RevokeAskEvent, RevokeEventHandler> {
    @Inject
    private RevokeEventConsumer consumer;

    @Override
    protected RevokeEventConsumer getConsumer() {
        return consumer;
    }
}
