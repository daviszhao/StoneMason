package io.github.daviszhao.stonemason.busEvent.services;

import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.event.NotifyEvent;
import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.util.CollectionUtils;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Named
@Slf4j
public class EventConsumer {
    @Inject
    private EventService service;
    private MultiHashMap<NotifyEventHandler> notifyEventHandlers = new MultiHashMap<>();

    private boolean accept(String eventType) {
        return notifyEventHandlers.containsKey(eventType) && !CollectionUtils.isEmpty(notifyEventHandlers.get(eventType));
    }

    @EventListener(NotifyEvent.class)
    public void handleNotifyEvent(NotifyEvent event) {
        if (accept(event.getType()))
            service.receivedEvent(event);
    }

    void registerHandler(NotifyEventHandler notifyEventHandler) {

        notifyEventHandlers.putHandler(notifyEventHandler.getType(), notifyEventHandler);
    }

    public void process(EventCategory eventcategory, EventPayload payload, String eventtype) {
        switch (eventcategory) {
            case NOTIFY:
                notifyEventHandlers.get(eventtype).forEach(handler -> handler.handle(payload));
                break;
            case ASK:
                break;
            case REVOKE:
                break;
            case ASKRESP:
                break;
        }
    }

    private static class MultiHashMap<H> extends HashMap<String, List<H>> {
        public void putHandler(String key, H handler) {
            log.debug("z注册事件处理程序{}:{}", key, handler);
            List<H> handlers = get(key);
            if (handlers == null) put(key, new ArrayList<>(Collections.singleton(handler)));
            else
                handlers.add(handler);
        }

        @Override
        public List<H> get(Object key) {
            return getOrDefault(key, Collections.emptyList());
        }
    }
}
