package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTradeQuery.*;

public interface EnterpriseTradeQueryService {
    BalancePayQueryResponseBody queryBalanacePayInfo(BalancePayQueryRequestBody req);

    BalanceRechargeQueryResponseBody queryBalanceRechargeInfo(BalanceRechargeQueryRequestBody req);

    EnterDefray2BankInfoQueryResponseBody queryDefray2BankInfo(EnterDefray2BankInfoQueryRequestBody req);

    BalanceExtractOrderResponseBody balanaceExtractOrder(BalanceExtractOrderRequestBody req);

    BalanceExtractQueryResponseBody queryBalanaceExtractInfo(BalanceExtractQueryRequestBody req);

    BalanceDefray2BankOrderResponseBody balanceDefray2BankOrder(BalanceDefray2BankOrderRequestBody req);

    BalancePayReceiveCustomQueryResponseBody queryBalanacePayReceiveCustomInfo(BalancePayReceiveCustomQueryRequestBody req);

    AccountPayAcquirerQueryResponseBody getAccountAcquirerPayInfo(AccountPayAcquirerQueryRequestBody req);

    BalanceDefray2BalanceQueryResponseBody balanceDefray2BalanceQuery(BalanceDefray2BalanceQueryRequestBody req);
}
