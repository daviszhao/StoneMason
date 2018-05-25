package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseQYJK.*;

public interface EnterpriseQYJKService {
    QYJKAccountInfoResponseBody accountInfo(QYJKAccountInfoRequestBody req);

    QYJKAccountStateResponseBody accountState(QYJKAccountStateRequestBody req);

    QYJKYieldRateResponseBody yieldRate7(QYJKYieldRateRequestBody req);

    QYJKYieldInfoResponseBody yieldInfo(QYJKYieldInfoRequestBody req);
}
