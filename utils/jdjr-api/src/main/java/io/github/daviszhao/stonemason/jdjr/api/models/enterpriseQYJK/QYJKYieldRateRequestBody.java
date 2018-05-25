package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class QYJKYieldRateRequestBody extends CommonRequest<QYJKYieldRateRequestBody> {
    public QYJKYieldRateRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
