package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

import java.util.List;

@lombok.Setter
@lombok.Getter
public class BankCnapsInfoResponseBody extends CommonResponse<BankCnapsInfoResponseBody> {
    private List<BankCnapsInfo> list;


}
