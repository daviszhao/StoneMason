package io.github.daviszhao.stonemason.busEvent.base;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

@Getter
@Setter
@NoArgsConstructor
public abstract class AbstractBusEvent extends RemoteApplicationEvent {
    private String payLoad;
    private String eventType;

    protected AbstractBusEvent(final Object source, final String originService, String payload, String eventtype) {
        super(source, originService);
        this.payLoad = payload;
        this.eventType = eventtype;
    }

    @JsonIgnore
    public abstract EventCategory getCategory();

}
