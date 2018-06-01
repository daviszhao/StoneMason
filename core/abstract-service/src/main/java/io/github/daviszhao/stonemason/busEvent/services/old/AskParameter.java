package io.github.daviszhao.stonemason.busEvent.services.old;

import io.github.daviszhao.stonemason.busEvent.event.AskEvent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Getter@Setter
public class AskParameter {
    private List<AskEvent> askEvents;
    private Class<?> callbackClass;
    private Map<String, String> extraParams;
    private Optional<LocalDateTime> timeoutTime;
    private boolean united;

}
