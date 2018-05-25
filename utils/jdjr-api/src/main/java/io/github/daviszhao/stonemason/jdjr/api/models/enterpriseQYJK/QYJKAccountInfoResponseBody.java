package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class QYJKAccountInfoResponseBody extends CommonResponse<QYJKAccountInfoResponseBody> {
    private Long accountBalance;//20
    private Long availableBalance;//20

}
