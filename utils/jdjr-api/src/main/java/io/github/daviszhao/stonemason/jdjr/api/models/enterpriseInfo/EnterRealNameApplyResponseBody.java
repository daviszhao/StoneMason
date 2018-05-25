package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

import java.util.Map;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyResponseBody extends CommonResponse<EnterRealNameApplyResponseBody> {
    private String resultCode, parterApplyId, jrApplyId;//20
    private Map<String, String> errorMap;

}
