package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyRejectRequestBody extends CommonRequest<EnterRealNameApplyRejectRequestBody> {
    private String merCustomerId;
    private String partnerApplyId;

    public EnterRealNameApplyRejectRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
