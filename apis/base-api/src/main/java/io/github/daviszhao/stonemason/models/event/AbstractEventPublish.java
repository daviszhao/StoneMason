package io.github.daviszhao.stonemason.models.event;

import io.github.daviszhao.stonemason.busEvent.constants.EventCategory;
import io.github.daviszhao.stonemason.busEvent.constants.ProcessStatus;
import io.github.daviszhao.stonemason.models.base.BaseModel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
abstract class AbstractEventPublish extends BaseModel {
    @NotNull
    private Integer id;
    @Size(max = 65535)
    private String payload;
    @Size(max = 20)
    private ProcessStatus status;
    @Size(max = 50)
    private String eventid;
    @NotNull
    @Size(max = 50)
    private String eventtype;
    private LocalDateTime createTime, updateTime;


    public abstract EventCategory getEventCategory();
}
