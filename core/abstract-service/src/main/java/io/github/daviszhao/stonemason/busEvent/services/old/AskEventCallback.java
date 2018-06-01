package io.github.daviszhao.stonemason.busEvent.services.old;

import io.github.daviszhao.stonemason.busEvent.payloads.FailureInfo;
import io.github.daviszhao.stonemason.models.event.AskRequstEventPublish;

import java.util.List;

public class AskEventCallback {
    public void call(EventRegistry eventRegistry, boolean success, List<AskRequstEventPublish> askEvents, String extraParams, FailureInfo failureInfo) {

    }
}
