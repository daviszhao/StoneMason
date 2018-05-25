package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo;

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterDownloadDefrayContractRequestBody extends CommonRequest<EnterDownloadDefrayContractRequestBody> {
    private String merCustomerId;//30

    public EnterDownloadDefrayContractRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
