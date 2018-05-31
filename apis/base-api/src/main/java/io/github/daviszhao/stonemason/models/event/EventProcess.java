
package io.github.daviszhao.stonemason.models.event;


import io.github.daviszhao.stonemason.models.base.BaseModel;
import lombok.*;

import javax.annotation.Generated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Generated(
        value = {
                "http://www.jooq.org",
                "jOOQ version:3.9.6"
        },
        comments = "This class is generated by jOOQ"
)
@SuppressWarnings({"all", "unchecked", "rawtypes"})
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(callSuper = false)
public class EventProcess extends BaseModel {

    private static final long serialVersionUID = 2075324387;

    @NotNull
    private Integer id;
    @Size(max = 65535)
    private String payload;
    @Size(max = 20)
    private String status;
    @NotNull
    @Size(max = 20)
    private String eventcategory;
    @Size(max = 50)
    private String eventid;
    @NotNull
    @Size(max = 50)
    private String eventtype;


    public EventProcess(EventProcess value) {
        this.id = value.id;
        this.payload = value.payload;
        this.status = value.status;
        this.eventcategory = value.eventcategory;
        this.eventid = value.eventid;
        this.eventtype = value.eventtype;
        this.setVersion(value.getVersion());
    }

}