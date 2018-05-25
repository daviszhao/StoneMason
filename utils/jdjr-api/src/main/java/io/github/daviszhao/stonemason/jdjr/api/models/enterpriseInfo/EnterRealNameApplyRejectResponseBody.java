package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonResponse;

import java.util.Map;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyRejectResponseBody extends CommonResponse<EnterRealNameApplyRejectResponseBody> {
    private String attachPath;
    private Map<String, String> rejectMap;

}
