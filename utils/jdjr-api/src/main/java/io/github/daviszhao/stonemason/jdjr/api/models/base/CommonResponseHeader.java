package io.github.daviszhao.stonemason.jdjr.api.models.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter

public class CommonResponseHeader implements Serializable {
    private String sdkVersion;//10
    private String version;//10
    private String msg;//50
    private String partnerId;//40
    private String merRequestNo;//30
    private String chartSet = "UTF-8";
    private String signType;//MD5，CERT
    private String sign;
    private String encryptType;
    private String encrypt;
    private String code;

}
