package io.github.daviszhao.stonemason.busEvent.services.consumers;

import io.github.daviszhao.stonemason.busEvent.event.RevokeAskEvent;
import io.github.daviszhao.stonemason.busEvent.services.handlers.RevokeEventHandler;
import lombok.extern.slf4j.Slf4j;

import javax.inject.Named;

@Named
@Slf4j
public class RevokeEventConsumer extends EventConsumer<RevokeAskEvent, RevokeEventHandler> {

}
