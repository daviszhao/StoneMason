package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterRealNameApplyRequestBody extends CommonRequest<EnterRealNameApplyRequestBody> {
    private String merCustomerId, partnerApplyId, companyType, blicCardType, blicCompanyName, blicUscc, blicCardNo, blicProvince, blicCity, blicAddress, blicScope;//30
    private String blicLongTerm, blicValidityEnd, blicUrlA, blicTrcCardNo, blicTrcLongTerm, blicTrcValidityEnd, blicTrcUrlA;
    private String blicObaCardNo, blicObaUrlA, lepCardType, lepName, lepCardNo, lepLongTerm, lepValidityEnd, lepUrlA, lepUrlB;
    private String aupIsLEP, aupCardType, aupName, aupCardNo, aupLongTerm, aupValidityEnd, aupUrlA, aupUrlB, aupPaUrlA;
    private String occUrl;

    public EnterRealNameApplyRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
