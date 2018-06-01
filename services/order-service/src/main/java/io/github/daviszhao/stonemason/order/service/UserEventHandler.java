package io.github.daviszhao.stonemason.order.service;

import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import io.github.daviszhao.stonemason.busEvent.services.NotifyEventHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class UserEventHandler extends NotifyEventHandler {
    @Override
    protected String getType() {
        return "event.user.test";
    }

    @Override
    public void handle(EventPayload payload) {
        log.debug("get event {}", payload);
    }
}
