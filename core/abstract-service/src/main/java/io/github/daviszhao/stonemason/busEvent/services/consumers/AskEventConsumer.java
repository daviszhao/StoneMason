package io.github.daviszhao.stonemason.busEvent.services.consumers;

import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import io.github.daviszhao.stonemason.busEvent.services.handlers.AskEventHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class AskEventConsumer extends EventConsumer<AskEvent, AskEventHandler> {

}
