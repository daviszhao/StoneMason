package io.github.daviszhao.stonemason.busEvent.event;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NotifyEvent extends AbstractBusEvent {


    public NotifyEvent(final Object source, String oriservice, String payload, String eventtype) {
        super(source, oriservice, payload, eventtype);
    }

    @Override
    public EventCategory getCategory() {
        return EventCategory.NOTIFY;
    }
}
