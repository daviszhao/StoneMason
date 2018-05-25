package io.github.daviszhao.stonemason.jdjr.api.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("com.jd.jr.config")
public class JDJRConfig {
    private String jd3DesKey = "testKeyasdfasdfasdfasdfasdfoilsdfjgsdfgjldfgjljljljl";
    private String charSet = "UTF-8";
    private String apiHost = "http://www.baidu.com";
    private String sdkVerion;
    private String version;
    private String partnerId;
    private String appId;
    private String signType;
    private String encryptType;
    private String signKey = "ajsdlfkajsdlfoqiweurlsdkljfaslkdjfas;ldfj";

    public String getJd3DesKey() {
        return jd3DesKey;
    }

    public void setJd3DesKey(String jd3DesKey) {
        this.jd3DesKey = jd3DesKey;
    }

    public String getCharSet() {
        return charSet;
    }

    public void setCharSet(String charSet) {
        this.charSet = charSet;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getSdkVerion() {
        return sdkVerion;
    }

    public void setSdkVerion(String sdkVerion) {
        this.sdkVerion = sdkVerion;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getPartnerId() {
        return partnerId;
    }

    public void setPartnerId(String partnerId) {
        this.partnerId = partnerId;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getEncryptType() {
        return encryptType;
    }

    public void setEncryptType(String encryptType) {
        this.encryptType = encryptType;
    }

    public String getSignKey() {
        return signKey;
    }

    public void setSignKey(String signKey) {
        this.signKey = signKey;
    }
}
