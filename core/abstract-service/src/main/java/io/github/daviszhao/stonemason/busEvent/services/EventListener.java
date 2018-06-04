package io.github.daviszhao.stonemason.busEvent.services;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.bus.ServiceMatcher;
import org.springframework.context.ApplicationListener;

import javax.inject.Named;

@Named
@Slf4j
@AllArgsConstructor
public class EventListener implements ApplicationListener<AbstractBusEvent> {
    private ServiceMatcher serviceMatcher;
    private EventService service;

    @Override
    public void onApplicationEvent(AbstractBusEvent event) {
        log.debug("事件类型：{}，事件种类：{}", event.getClass(), event.getCategory());
        if (!serviceMatcher.isFromSelf(event)) {

            service.receivedEvent(event);
        } else {
            log.debug("自己发的，不关心");
        }
    }
}
