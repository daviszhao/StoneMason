package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
class BankProvince implements Serializable {
    private String provinceCode, provinceName;

}
