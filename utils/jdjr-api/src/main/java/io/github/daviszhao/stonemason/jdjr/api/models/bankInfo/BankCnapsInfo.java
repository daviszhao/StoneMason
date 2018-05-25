package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import java.io.Serializable;

@lombok.Setter
@lombok.Getter
public class BankCnapsInfo implements Serializable {
    private String bankCode, bankCnapsName, bankCnapsCode;

}
