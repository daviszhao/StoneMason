package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
class BankInfo implements Serializable {
    private String bankCode, bankName;

}
