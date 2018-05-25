package io.github.daviszhao.stonemason.jdjr.api.models.enterpriseTrade;//

import io.github.daviszhao.stonemason.jdjr.api.models.base.CommonRequest;

@lombok.Setter
@lombok.Getter
public class EnterAccountDefray2BankAccRequestBody extends CommonRequest<EnterAccountDefray2BankAccRequestBody> {
    private String merOrderNo;//String(30);//否;//商户业务请求订单号（唯一）
    private long orderAmount;//Long;//否;//单位：分
    private String bankCode;//String;//否;//银行编码
    private String bankName;//String;//否;//银行名称
    private String bankAccountName;//String;//否;//银行账户名称
    private String BankAccountNo;//String;//否;//银行账户号
    private String memo;//String;//否;//描述信息
    private String cardType;//String;//否;//DEBIT：借记卡；
    // CREDIT：贷记卡;//
    // BANK_ACCOUNT:;//银行账户
    private String bankAccountType;//String;//否;//FOR_CORPORATE：对公；
    // FOR_PERSONAL：对私
    private String city;//String;//是;//开户行所在市
    private String province;//String;//是;//开户行所在省
    private String bankCnaps;//String;//是;//银行联行编码
    private String subsidiaryCode;//String;//是;//分支机构编码
    private String subsidiaryName;//String;//是;//分支机构名称
    private String jrMerMobile;//

    public EnterAccountDefray2BankAccRequestBody() {
        super();
    }

    @Override
    public String getRequestUrl() {
        return null;
    }
}
