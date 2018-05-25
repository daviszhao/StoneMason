package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class QYJKAccountStateRequestBody extends CommonRequest<QYJKAccountStateRequestBody> {
    private String merCustomerId;

    public QYJKAccountStateRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
