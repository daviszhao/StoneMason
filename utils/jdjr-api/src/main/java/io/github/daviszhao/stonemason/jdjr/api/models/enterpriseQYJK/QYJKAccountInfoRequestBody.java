package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class QYJKAccountInfoRequestBody extends CommonRequest<QYJKAccountInfoRequestBody> {
    private String merCustomerId;

    public QYJKAccountInfoRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
