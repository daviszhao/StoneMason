package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class QYJKYieldInfoRequestBody extends CommonRequest<QYJKYieldInfoRequestBody> {
    public QYJKYieldInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
