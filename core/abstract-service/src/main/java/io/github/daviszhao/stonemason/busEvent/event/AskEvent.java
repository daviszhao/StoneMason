package io.github.daviszhao.stonemason.busEvent.event;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AskEvent extends AbstractBusEvent {
    private String id;


    @Override
    public EventCategory getCategory() {
        return EventCategory.ASK;
    }
}
