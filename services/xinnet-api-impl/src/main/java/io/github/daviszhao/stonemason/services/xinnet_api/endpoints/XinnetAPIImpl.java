package io.github.daviszhao.stonemason.services.xinnet_api.endpoints;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.daviszhao.stonemason.services.xinnet_api.XinnetAPI;
import io.github.daviszhao.stonemason.services.xinnet_api.config.XinnetAPIConfig;
import io.github.daviszhao.stonemason.services.xinnet_api.vo.AgentInfoFromAPI;
import io.github.daviszhao.stonemason.services.xinnet_api.vo.GoodsDetail;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.util.StringUtils.hasText;

@RestController
@Slf4j
public class XinnetAPIImpl implements XinnetAPI {
    @Inject
    private RestTemplate restTemplate;
    @Value("${spring.profiles.active:default}")
    private String profile;
    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private XinnetAPIConfig apiConfig;


    /**
     * 根据agentcode获取会员信息
     *
     * @param agentCode
     * @return
     */
    @Override
    public AgentInfoFromAPI getAgentInfo(String agentCode) {
        assert hasText(agentCode);

        final String uri = "/x-api/user/query";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (apiSucceed(data)) {
            AgentInfoFromAPI result = new AgentInfoFromAPI();

            result.setUserMobilePhone((String) data.get("agentMobileNumber"));
            result.setRealNamed("true".equalsIgnoreCase((String) data.get("authenticateFlag")));
            result.setUserNameEmail((String) data.get("agentEmail"));

            return result;
        } else {
            String message = String.format("从新网平台同步用户信息：agentCode:%s.失败:[%s]", agentCode, data);
            throw new RuntimeException(message);
        }
    }

    /**
     * 根据agentcode获取会员余额
     *
     * @param agentCode
     * @return
     */
    @Override
    public double getAgentBalance(String agentCode) {
        assert hasText(agentCode);

        final String uri = "/x-api/fin/accountQuery";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (apiSucceed(data)) {

            return (double) data.get("totalBalance");
        } else {
            String message = String.format("从新网平台同步会员余额：agentCode:%s.失败:[%s]", agentCode, data);
            throw new RuntimeException(message);
        }
    }

    private boolean apiSucceed(Map<String, Object> apiResponse) {
        return apiResponse != null && !apiResponse.containsKey("code");
    }

    /**
     * 发送短信
     */
    @Override
    public void sendSms(String content, String agentCode) {
        assert hasText(agentCode);
        assert hasText(content);
        log.debug("发送短信：【agentCode={},content={}】", agentCode, content);
        if ("testcase".equalsIgnoreCase(profile)) return;
        String enviromentTag = "[" + profile + "]";
        final String uri = "/x-api/smsc/send";
        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);
        argsMap.put("content", enviromentTag + content);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);
        if (!apiSucceed(data))
            log.debug("通过新网平台发送短信：【agentCode={},content={}】.失败:[{}]", agentCode, content, data);
    }

    /**
     * 通知 平台冻结金额
     *
     * @param agentCode
     * @param amount
     * @return freezedetailCode
     */
    @Override
    public String freeze(String agentCode, final double amount) {
        log.debug("从平台冻结");
        assert hasText(agentCode);
        assert amount >= 0;
        final String uri = "/x-api/fin/tradefreeze";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);

        argsMap.put("amount", String.valueOf(amount));
        argsMap.put("description", String.format("对%s冻结%s", agentCode, amount));

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (!apiSucceed(data)) {
            String message = String.format("通知 平台冻结金额：agentCode:%s,amount=%s.失败:[%s]", agentCode, amount, data);
            throw new RuntimeException(message);
        }
        return (String) data.get("freezedetailCode");
    }

    /**
     * 通知 平台充值金额
     *
     * @param agentCode
     * @param type
     * @param bank
     * @param amount
     * @return inpourdetailCode
     */
    @Override
    public String inpour(String agentCode, String type, String bank, final double amount) {
        log.debug("向平台充值");
        assert hasText(agentCode);
        assert ALIPAY.equals(type) || WEIXINPAY.equals(type);
        assert amount > 0;
        final String uri = "/x-api/fin/tradeinpour";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);

        argsMap.put("amount", String.valueOf(amount));
        argsMap.put("type", type);
        if (ALIPAY.equals(type) && hasText(bank))
            argsMap.put("bank", bank);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (!apiSucceed(data)) {
            String message = String.format("通知 平台充值金额：agentCode:%s,amount=%s.失败:[%s]", agentCode, amount, data);
            throw new RuntimeException(message);
        }
        return (String) data.get("res");
    }

    /**
     * 通知 平台解冻金额
     *
     * @param agentCode
     * @param freezedetailCode
     * @return
     */
    @Override
    public void unfreeze(String agentCode, String freezedetailCode) {
        assert hasText(agentCode);
        assert hasText(freezedetailCode);
        log.debug("从平台解冻");

        final String uri = "/x-api/fin/tradeunfreeze";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);
        argsMap.put("freezedetailCode", freezedetailCode);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (!apiSucceed(data)) {
            String message = String.format("通知 平台解冻金额：agentCode:%s,freezedetailCode=%s.失败:[%s]", agentCode, freezedetailCode, data);
            throw new RuntimeException(message);
        }
    }

    /**
     * 通知 平台扣款
     *
     * @param agentCode
     * @param serviceCode
     * @param price
     * @param marketPrice
     * @param timeAmount
     * @param timeUnit         M 月 Y 年 C 次
     * @param freezedetailCode
     * @return
     */
    @Override
    public String pay(String agentCode, String serviceCode, double price, double marketPrice, int timeAmount, String timeUnit, String freezedetailCode) {
        log.debug("从平台扣款");
        assert hasText(agentCode);
        assert hasText(serviceCode);
        assert hasText(freezedetailCode);
        assert price >= 0;
        assert marketPrice >= 0;
        final String uri = "/x-api/fin/tradepay";

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);
        argsMap.put("isInvoice", "false");
        String goodsDetailString = null;
        try {
            goodsDetailString = objectMapper.writeValueAsString(new GoodsDetail[]{new GoodsDetail(apiConfig.getGoodsCode(), apiConfig.getProductCode(), serviceCode, apiConfig.getProductType(), price, marketPrice, timeAmount, timeUnit, freezedetailCode, null)});
        } catch (JsonProcessingException ignored) {

        }
        argsMap.put("goodsDetail", goodsDetailString);

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (apiSucceed(data)) {
            @SuppressWarnings("unchecked")
            List<Map<String, String>> orderList = (List<Map<String, String>>) data.get("orderList");
            if (orderList != null && orderList.size() == 1) {
                Map<String, String> orderdata = orderList.get(0);
                if (orderdata.get("state").equals("0")) {
                    return orderdata.get("orderCode");
                }
            }
        }
        throw new RuntimeException(String.format("通知 平台扣款：agentCode:%s,goodsDetailString=%s.失败:[%s]", agentCode, goodsDetailString, data));
    }

    private Map<String, Object> callXinnetAPI(String url, Map<String, String> argsMap) {
        //获取待签名字符串
        String data = createLinkString(argsMap);


        argsMap.put("appKey", apiConfig.getXinnetApiAppKey());

        String sign = createSign(argsMap);
        String fullUrl = apiConfig.getXinnetApiDomainName() + url + ("?appKey=" + apiConfig.getXinnetApiAppKey() + "&sign=" + sign);
        log.debug("Calling Xinet API :[fullUrl:{},args:【{}】]", fullUrl, data);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<String> entity = new HttpEntity<>(data, httpHeaders);

        ResponseEntity<String> result = restTemplate.postForEntity(fullUrl, entity, String.class);

        log.debug("rest result:{}", result);
        assert result != null;
        if (result.getStatusCode() == HttpStatus.OK) {
            try {
                Map<String, Object> resultMap = objectMapper.readValue(result.getBody(), new TypeReference<Map<String, Object>>() {
                });
                log.debug("Call result:{}", resultMap);
                return resultMap;
            } catch (IOException e) {
                log.error("接口" + url + "调用失败:{}。", e.getMessage());
            }
        } else
            log.error("接口" + url + "调用失败:{}。", result.getStatusCode());
        return null;

    }

    private String createSign(Map<String, String> argsMap) {
        Map<String, String> sParaNew = paraFilter(argsMap);
        String linkString = createLinkString(sParaNew);
        return DigestUtils.md5Hex(linkString + apiConfig.getXinnetApiSecret());
    }

    private Map<String, String> paraFilter(Map<String, String> params) {

        return params == null ? null : params.entrySet().stream().filter(entry -> hasText(entry.getValue()) && !entry.getKey().equalsIgnoreCase("sign")).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

    }

    private String createLinkString(Map<String, String> params) {
        return params.entrySet().stream()
                .sorted(Comparator.comparing(Map.Entry::getKey))
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.joining("&"));

    }

    /**
     * 通知 平台退款
     */
    @Override
    public String refund(String agentCode, String orderCode, double refundAmount) {
        log.debug("平台退款");
        final String uri = "/x-api/fin/refund";
        assert hasText(agentCode);
        assert hasText(orderCode);
        assert refundAmount >= 0;

        Map<String, String> argsMap = new HashMap<>();
        argsMap.put("agentCode", agentCode);
        argsMap.put("orderCode", orderCode);
        argsMap.put("refundAmount", String.valueOf(refundAmount));

        Map<String, Object> data = callXinnetAPI(uri, argsMap);

        if (!apiSucceed(data)) {
            String message = String.format("通知 平台退款：agentCode:%s,orderCode=%s.失败:[%s]", agentCode, orderCode, data);
            throw new RuntimeException(message);
        }
        //返回退款定单编号记录到退款定单里.
        return (String) data.get("orderCode");
    }
}
