package io.github.daviszhao.stonemason.jdjr.api.models.base;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class Response {
    String header, body;
}
