package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
class BankCity implements Serializable {
    private String cityCode, cityName;

}
