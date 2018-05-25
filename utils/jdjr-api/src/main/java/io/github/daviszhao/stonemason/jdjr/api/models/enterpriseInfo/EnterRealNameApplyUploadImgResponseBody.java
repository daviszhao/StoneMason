package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyUploadImgResponseBody extends CommonResponse<EnterRealNameApplyUploadImgResponseBody> {
    private String attachPath;

}
