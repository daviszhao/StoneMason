package io.github.daviszhao.stonemason.busEvent.base;

import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.payloads.EventPayload;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractBusEvent extends RemoteApplicationEvent {
    private EventPayload payLoad;
    private String id;
    private String type;

    protected static String generateEventID() {
        return UUID.randomUUID().toString();
    }

    public abstract EventCategory getCategory();

}
