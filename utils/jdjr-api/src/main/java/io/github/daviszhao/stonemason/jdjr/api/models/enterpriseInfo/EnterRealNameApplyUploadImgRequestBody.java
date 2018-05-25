package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyUploadImgRequestBody extends CommonRequest<EnterRealNameApplyUploadImgRequestBody> {
    private String merCustomerId;
    private String partnerApplyId;
    private String base64Text;
    private String attachType;

    public EnterRealNameApplyUploadImgRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
