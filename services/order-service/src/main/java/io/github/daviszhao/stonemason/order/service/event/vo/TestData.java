package io.github.daviszhao.stonemason.order.service.event.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class TestData {
    private String name;
    private LocalDateTime time;
}
