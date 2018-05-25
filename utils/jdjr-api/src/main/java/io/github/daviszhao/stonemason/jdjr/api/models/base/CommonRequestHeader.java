package io.github.daviszhao.stonemason.jdjr.api.models.base;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@lombok.Setter
@lombok.Getter
public class CommonRequestHeader implements Serializable {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
    private String sdkVersion;//10
    private String version;//10
    private String partnerId;//40
    private String appId;//10
    private String merRequestNo;//30
    private String merRequestTime = TIME_FORMATTER.format(LocalDateTime.now());
    private String chartSet = "UTF-8";
    private String signType;//MD5，CERT
    private String sign;
    private String encryptType;
    private String encrypt;
}
