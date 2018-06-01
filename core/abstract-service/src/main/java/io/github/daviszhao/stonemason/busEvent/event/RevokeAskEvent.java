package io.github.daviszhao.stonemason.busEvent.event;

import io.github.daviszhao.stonemason.busEvent.base.AbstractBusEvent;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.payloads.FailureInfo;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RevokeAskEvent extends AbstractBusEvent {
    private FailureInfo failureInfo;
    private String askEventId;

    public RevokeAskEvent(FailureInfo failureInfo, String askEventId) {
        this.failureInfo = failureInfo;
        this.askEventId = askEventId;
    }


    @Override
    public EventCategory getCategory() {
        return EventCategory.REVOKE;
    }
}
