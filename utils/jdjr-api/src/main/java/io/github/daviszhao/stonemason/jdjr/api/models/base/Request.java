package io.github.daviszhao.stonemason.jdjr.api.models.base;

import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@ToString
@NoArgsConstructor
public class Request {
    String header, body;
}
