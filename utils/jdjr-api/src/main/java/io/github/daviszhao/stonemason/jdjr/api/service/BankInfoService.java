package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.bankInfo.*;

public interface BankInfoService {
    BankInfoResponseBody queryBankInfo(BankInfoRequestBody requestBody);

    BankProvinceResponseBody queryProvince(BankProvinceRequestBody requestBody);

    BankCityRequestBody queryCity(BankCityResponseBody requestBody);

    BankCnapsInfoResponseBody queryBankCnapsInfo(BankCnapsInfoRequestBody requestBody);

    BankSupportListResponseBody queryBankSupportList(BankSupportListRequestBody requestBody);
}
