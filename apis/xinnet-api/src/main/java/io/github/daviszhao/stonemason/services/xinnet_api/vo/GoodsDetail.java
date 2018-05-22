package io.github.daviszhao.stonemason.services.xinnet_api.vo;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Setter
@Getter
@AllArgsConstructor
public class GoodsDetail implements Serializable {
    private String goodsCode;
    private String productCode;
    private String serviceCode;
    private String productType;
    private double price;
    private double originalPrice;
    private int timeAmount;
    private String timeUnit;
    private String freezedetailCode;
    private String domainName;
/*

    public GoodsDetail(String goodsCode, String productCode, String serviceCode, String productType, double price, double originalPrice, int timeAmount, String timeUnit, String freezedetailCode) {
        this.goodsCode = goodsCode;
        this.productCode = productCode;
        this.serviceCode = serviceCode;
        this.productType = productType;
        this.price = price;
        this.originalPrice = originalPrice;
        this.timeAmount = timeAmount;
        this.timeUnit = timeUnit;
        this.freezedetailCode = freezedetailCode;
    }

    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

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

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public int getTimeAmount() {
        return timeAmount;
    }

    public void setTimeAmount(int timeAmount) {
        this.timeAmount = timeAmount;
    }

    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getFreezedetailCode() {
        return freezedetailCode;
    }

    public void setFreezedetailCode(String freezedetailCode) {
        this.freezedetailCode = freezedetailCode;
    }
*/

}
