package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseAccount.EnterAccountInfoRequestBody;
import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseAccount.EnterAccountInfoResponseBody;

public interface EnterpriseAccountService {
    /**
     * 、 企业 资金 账户信息查询 接口
     *
     * @param request
     * @return
     */
    EnterAccountInfoResponseBody queryAccountInfo(EnterAccountInfoRequestBody request);
}
