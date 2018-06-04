package io.github.daviszhao.stonemason.jdjr.api.models.base;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
abstract class AsyncNotifyMerMessage<T> implements Serializable {
    private AsyncNotifyMerMessageHeader messageHeader;


    private T messageBody;
}
