package io.github.daviszhao.stonemason.busEvent.services.consumers;

import io.github.daviszhao.stonemason.busEvent.event.AskResponseEvent;
import io.github.daviszhao.stonemason.busEvent.services.handlers.AskRespEventHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class AskRespEventConsumer extends EventConsumer<AskResponseEvent, AskRespEventHandler> {

}
