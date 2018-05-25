package io.github.daviszhao.stonemason.jdjr.api.service.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.daviszhao.stonemason.jdjr.api.models.base.*;
import io.github.daviszhao.stonemason.jdjr.api.service.config.JDJRConfig;
import io.github.daviszhao.stonemason.jdjr.api.service.impls.SecurityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.UUID;

@Named
@Slf4j
public class APIInvoker {
    @Inject
    private JDJRConfig jdConfig;
    @Inject
    private SecurityService securityService;
    @Inject
    private ObjectMapper objectMapper;
    @Inject
    private RestTemplateBuilder restTemplateBuilder;

    public <REQ extends CommonRequest<REQ>, RESP extends CommonResponse<RESP>> RESP invoke(REQ req, Class<RESP> respClass) throws Exception {
        String bodyEncrypted = encrypt(req);
        System.out.println(bodyEncrypted);
        CommonRequestHeader header = fillRequestHeader(req, bodyEncrypted);


        String headString = objectMapper.writeValueAsString(header);
        Request request = new Request(headString, bodyEncrypted);
        RestTemplate restTemplate = restTemplateBuilder.build();
        Response response = restTemplate.postForObject(jdConfig.getApiHost() + req.getRequestUrl(), request, Response.class);
        checkSign(response.getHeader());

        return decrypt(response.getBody(), respClass);
    }

    private void checkSign(final String responseHeader) throws IOException {
        CommonResponseHeader header = objectMapper.readValue(responseHeader, CommonResponseHeader.class);
        if (!verifySign(header)) throw new RuntimeException("sign error");
    }

    private <REQ extends CommonRequest<REQ>> CommonRequestHeader fillRequestHeader(REQ req, String bodyEncrypted) throws UnsupportedEncodingException {
        CommonRequestHeader requestHeader = req.getRequestHeader();
        requestHeader.setSdkVersion(jdConfig.getSdkVerion());
        requestHeader.setVersion(jdConfig.getVersion());
        requestHeader.setPartnerId(jdConfig.getPartnerId());
        requestHeader.setAppId(jdConfig.getAppId());
        requestHeader.setMerRequestNo(UUID.randomUUID().toString());//todo 请求号，应该有幂等性？
        requestHeader.setSignType(jdConfig.getSignType());
        requestHeader.setEncryptType(jdConfig.getEncryptType());

        requestHeader.setEncrypt(bodyEncrypted);

        return sign(requestHeader);
    }

    private CommonRequestHeader sign(CommonRequestHeader commonRequestHeader) throws UnsupportedEncodingException {
        String tosign = "sdkVersion=" + commonRequestHeader.getSdkVersion()
                + "&version=" + commonRequestHeader.getVersion()
                + "&partnerId=" + commonRequestHeader.getPartnerId()
                + "&appId=" + commonRequestHeader.getAppId()
                + "&merRequestNo=" + commonRequestHeader.getMerRequestNo()
                + "&merRequestTime=" + commonRequestHeader.getMerRequestTime()
                + "&charset=" + commonRequestHeader.getChartSet()
                + "&signType=" + commonRequestHeader.getSignType()
                + "&encryptType=" + commonRequestHeader.getEncryptType()
                + "&encrypt=" + commonRequestHeader.getEncrypt();
        String sign = securityService.generateSign(tosign);
        System.out.println("sing=" + sign);
        commonRequestHeader.setSign(sign);
        return commonRequestHeader;
    }

    private boolean verifySign(CommonResponseHeader commonResponseHeader) {
        String tosign = "sdkVersion=" + commonResponseHeader.getSdkVersion()
                + "&version=" + commonResponseHeader.getVersion()
                + "&code=" + commonResponseHeader.getCode()
                + "&msg=" + commonResponseHeader.getMsg()
                + "&partnerId=" + commonResponseHeader.getPartnerId()
                + "&merRequestNo=" + commonResponseHeader.getMerRequestNo()
                + "&charset=" + commonResponseHeader.getChartSet()
                + "&signType=" + commonResponseHeader.getSignType()
                + "&encryptType=" + commonResponseHeader.getEncryptType()
                + "&encrypt=" + commonResponseHeader.getEncrypt();
        return securityService.verifySign(tosign, commonResponseHeader.getSign());

    }

    private <REQ extends CommonRequest<REQ>> String encrypt(REQ obj) throws Exception {
        if (obj == null) return "{}";
        return securityService.encrypt(objectMapper.writeValueAsString(obj));
    }

    private <RESP extends CommonResponse<RESP>> RESP decrypt(String encrypted, Class<RESP> clazz) throws Exception {
        if (encrypted == null || "{}".equals(encrypted)) return null;
        return objectMapper.readValue(securityService.decrypt(encrypted), clazz);
    }
}
