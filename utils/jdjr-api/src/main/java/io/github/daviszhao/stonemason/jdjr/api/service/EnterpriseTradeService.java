package io.github.daviszhao.stonemason.jdjr.api.service;

import io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade.*;

public interface EnterpriseTradeService {
    BanlancePayOrderResponseBody balanacePayOrder(BanlancePayOrderRequestBody request);

    BalanceRechargeOrderResponseBody balanceRechargeOrder(BalanceRechargeOrderRequestBody request);

    EnterAccountDefray2BankAccResponseBody defray2BankAcc(EnterAccountDefray2BankAccRequestBody requestBody);

    AccountPayAcquirerResponseBody getAccountPayAcquirer(AccountPayAcquirerRequestBody requestBody);

    BalanceDefray2BalanceOrderResponseBody balanceDefray2BalanceOrder(BalanceDefray2BalanceOrderRequestBody requestBody);
}
