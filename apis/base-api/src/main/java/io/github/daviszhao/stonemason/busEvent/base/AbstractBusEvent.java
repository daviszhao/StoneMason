package io.github.daviszhao.stonemason.busEvent.base;

import lombok.Getter;
import lombok.Setter;
import org.springframework.cloud.bus.event.RemoteApplicationEvent;

import java.util.Map;

@Getter
@Setter
public abstract class AbstractBusEvent extends RemoteApplicationEvent {
    private Map<String, String> data;
}
