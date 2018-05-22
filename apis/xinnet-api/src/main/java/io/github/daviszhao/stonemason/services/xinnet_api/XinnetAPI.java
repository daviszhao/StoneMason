package io.github.daviszhao.stonemason.services.xinnet_api;

import io.github.daviszhao.stonemason.services.xinnet_api.vo.AgentInfoFromAPI;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(serviceId = "xinnet-api")
public interface XinnetAPI {
    String WEIXINPAY = "weixinpay";
    String ALIPAY = "alipay";
    String TIMEUNIT_C = "C";
    String TIMEUNIT_Y = "Y";
    String TIMEUNIT_M = "M";

    @RequestMapping("getAgentInfo")
    AgentInfoFromAPI getAgentInfo(@RequestParam("agentCode") String agentCode);

    @RequestMapping("getAgentBalance")
    double getAgentBalance(@RequestParam("agentCode") String agentCode);

    @RequestMapping("sendSms")
    void sendSms(@RequestParam("content") String content,
                 @RequestParam("agentCode") String agentCode);

    @RequestMapping("freeze")
    String freeze(@RequestParam("agentCode") String agentCode,
                  @RequestParam("amount") double amount);

    @RequestMapping("inpour")
    String inpour(@RequestParam("agentCode") String agentCode,
                  @RequestParam("type") String type,
                  @RequestParam("bank") String bank,
                  @RequestParam("amount") double amount);

    @RequestMapping("unfreeze")
    void unfreeze(@RequestParam("agentCode") String agentCode,
                  @RequestParam("freezedetailCode") String freezedetailCode);

    @RequestMapping("pay")
    String pay(@RequestParam("agentCode") String agentCode,
               @RequestParam("serviceCode") String serviceCode,
               @RequestParam("price") double price,
               @RequestParam("marketPrice") double marketPrice,
               @RequestParam("timeAmount") int timeAmount,
               @RequestParam("timeUnit") String timeUnit,
               @RequestParam("content") String freezedetailCode);

    @RequestMapping("refund")
    String refund(@RequestParam("agentCode") String agentCode,
                  @RequestParam("orderCode") String orderCode,
                  @RequestParam("content") double refundAmount);
}
