package io.github.daviszhao.stonemason.busEvent.payloads;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class FailureInfo implements Serializable {
    private FailureReason reason;
    private LocalDateTime time;
}
