package io.github.daviszhao.stonemason.jdjr.api.models.base;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class AsyncNotifyMerMessageHeader implements Serializable {
    public static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    private String messageType;//10
    private String messageVersion;//10
    private String messageSendNo;//50
    private String messageSendTime = TIME_FORMATTER.format(LocalDateTime.now());
    private String chartSet = "UTF-8";
    private String signType;//MD5，CERT
    private String sign;
    private String encryptType;

    @Override
    public String toString() {
        return "AsyncNotifyMerMessageHeader{" +
                "messageType='" + messageType + '\'' +
                ", messageVersion='" + messageVersion + '\'' +
                ", messageSendNo='" + messageSendNo + '\'' +
                ", messageSendTime='" + messageSendTime + '\'' +
                ", chartSet='" + chartSet + '\'' +
                ", signType='" + signType + '\'' +
                ", sign='" + sign + '\'' +
                ", encryptType='" + encryptType + '\'' +
                '}';
    }
}
