package io.github.daviszhao.stonemason.busEvent.event;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AskResponseEvent extends AbstractBusEvent {
    private boolean success;
    private String message;
    private String askEventId;

    public AskResponseEvent(boolean success, String message, String askEventId) {
        this.success = success;
        this.message = message;
        this.askEventId = askEventId;
    }


    @Override
    public EventCategory getCategory() {
        return EventCategory.ASKRESP;
    }
}
