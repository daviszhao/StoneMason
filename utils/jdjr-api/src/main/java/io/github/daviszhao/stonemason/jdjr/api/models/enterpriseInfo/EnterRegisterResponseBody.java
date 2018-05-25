package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterRegisterResponseBody extends CommonResponse<EnterRegisterResponseBody> {
    private String jrMerchantId;//20
    private String alais;//20

}
