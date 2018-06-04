package io.github.daviszhao.stonemason.busEvent.services.consumers;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.services.EventService;
import io.github.daviszhao.stonemason.busEvent.services.handlers.AbstractEventHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Slf4j
public abstract class EventConsumer<E extends AbstractBusEvent, H extends AbstractEventHandler<E, H>> {
    private final MultiHashMap<H> eventHandlers = new MultiHashMap<>();
    @Inject
    private EventService service;

    private boolean accept(String eventType) {
        return eventHandlers.containsKey(eventType) && !CollectionUtils.isEmpty(eventHandlers.getOrDefault(eventType, Collections.emptyList()));
    }

    public void registerHandler(H h) {

        eventHandlers.putHandler(h.getType(), h);
    }

    public void process(String payload, String eventtype) {
        eventHandlers.getOrDefault(eventtype, Collections.emptyList()).forEach(handler -> handler.handle(payload));
    }


    private static class MultiHashMap<H> extends HashMap<String, List<H>> {
        void putHandler(String key, H handler) {
            log.debug("注册事件处理程序{}:{}", key, handler);
            List<H> handlers = get(key);
            if (handlers == null) put(key, new ArrayList<>(Collections.singleton(handler)));
            else
                handlers.add(handler);
        }

    }
}
