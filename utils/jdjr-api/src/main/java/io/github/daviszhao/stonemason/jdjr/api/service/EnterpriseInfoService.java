package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseInfo.*;

public interface EnterpriseInfoService {

    EnterQueryInfoResponseBody query(EnterQueryInfoRequestBody request);

    /**
     * 企业账户注册 接口
     *
     * @param request
     * @return
     */
    EnterRegisterResponseBody register(EnterRegisterRequestBody request);

    EnterModifyInfoResponseBody modify(EnterModifyInfoRequestBody request);

    EnterQueryDefrayAuthResponseBody queryDefrayAuth(EnterQueryDefrayAuthRequestBody request);

    EnterDownloadDefrayContractResponseBody downLoadContract(EnterQueryDefrayAuthRequestBody request);

    EnterRealNameApplyResponseBody realNameApply(EnterRealNameApplyRequestBody request);

    EnterRealNameApplyUploadImgResponseBody realNameApplyUploadImg(EnterRealNameApplyUploadImgRequestBody request);

    EnterRealNameApplyRejectResponseBody realNameApplyReject(EnterRealNameApplyRejectRequestBody request);
}
