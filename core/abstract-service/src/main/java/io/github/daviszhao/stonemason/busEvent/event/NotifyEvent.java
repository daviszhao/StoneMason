package io.github.daviszhao.stonemason.busEvent.event;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotifyEvent extends AbstractBusEvent {

    public NotifyEvent(EventPayload payload, String eventtype) {
        this(payload, generateEventID(), eventtype);
    }

    public NotifyEvent(EventPayload payload, String eventid, String eventtype) {
        super(payload, eventid, eventtype);
    }

    @Override
    public EventCategory getCategory() {
        return EventCategory.NOTIFY;
    }
}
