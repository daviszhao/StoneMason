package io.github.daviszhao.stonemason.jdjr.api.models.bankInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

import java.util.List;

@lombok.Setter
@lombok.Getter
public class BankCityResponseBody extends CommonResponse<BankCityResponseBody> {
    private List<BankCity> list;


}
