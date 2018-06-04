package io.github.daviszhao.stonemason.order.service.event.handlers;


import io.github.daviszhao.stonemason.busEvent.services.handlers.NotifyEventHandler;
import io.github.daviszhao.stonemason.busEvent.services.utils.EventUtils;
import io.github.daviszhao.stonemason.order.service.event.vo.TestData;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class UserTestEventHandler extends NotifyEventHandler {
    @Inject
    private EventUtils eventUtils;

    @Override
    protected NotifyEventHandler getInstance() {
        return this;
    }

    @Override
    public String getType() {
        return "event.user.test";
    }

    @Override
    public void handle(String payload) {
        System.out.println(eventUtils.deserialize(payload, TestData.class));
    }
}
