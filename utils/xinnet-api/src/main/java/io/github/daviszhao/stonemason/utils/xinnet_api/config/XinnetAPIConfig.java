package io.github.daviszhao.stonemason.utils.xinnet_api.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("market.xinnet.api.config")
public class XinnetAPIConfig {
    private String goodsCode;
    private String productCode;
    private String productType;
    private String xinnetApiAppKey;
    private String xinnetApiDomainName;
    private String xinnetApiSecret;

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getProductCode() {
        return productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getXinnetApiAppKey() {
        return xinnetApiAppKey;
    }

    public void setXinnetApiAppKey(String xinnetApiAppKey) {
        this.xinnetApiAppKey = xinnetApiAppKey;
    }

    public String getXinnetApiDomainName() {
        return xinnetApiDomainName;
    }

    public void setXinnetApiDomainName(String xinnetApiDomainName) {
        this.xinnetApiDomainName = xinnetApiDomainName;
    }

    public String getXinnetApiSecret() {
        return xinnetApiSecret;
    }

    public void setXinnetApiSecret(String xinnetApiSecret) {
        this.xinnetApiSecret = xinnetApiSecret;
    }
}
