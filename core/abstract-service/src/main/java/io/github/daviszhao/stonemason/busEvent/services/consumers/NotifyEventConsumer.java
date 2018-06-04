package io.github.daviszhao.stonemason.busEvent.services.consumers;

import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.services.handlers.NotifyEventHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class NotifyEventConsumer extends EventConsumer<NotifyEvent, NotifyEventHandler> {

}
