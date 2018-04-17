package com.s2u2m.slancer.account.otherService.wechat;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.otherService.wechat.dto.WechatSession;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.Map;

/**
 * @author Amos Xia
 */
@Service
public class WechatService {

    @Autowired
    private WechatServiceProperty wechatServiceProperty;

    /**
     * details in https://developers.weixin.qq.com/miniprogram/dev/api/api-login.html#wxloginobject
     *
     * @return WechatSession
     */
    public WechatSession js2Session(String jsCode) {
        UriComponentsBuilder builder = UriComponentsBuilder
                .fromHttpUrl("https://api.weixin.qq.com/sns/jscode2session?")
                .queryParam("appid", wechatServiceProperty.getAppId())
                .queryParam("secret", wechatServiceProperty.getSecret())
                .queryParam("js_code", jsCode)
                .queryParam("grant_type", "authorization_code");

        RestTemplate restTemplate = new RestTemplate();
        String resp = restTemplate.getForObject(
                builder.build().encode().toUriString(), String.class);

        ObjectMapper mapper = new ObjectMapper();
        TypeReference<Map<String, String>> reference =
                new TypeReference<Map<String, String>>() {};

        try {
            Map<String, String> rst = mapper.readValue(resp, reference);
            if (rst.containsKey("errcode")) {
                throw ExceptionBuilder.build(AccountErrorCode.OtherServiceWechatFailed, resp);
            }

            String openId = rst.get("openid");
            String sessionKey = rst.get("session_key");
            WechatSession session = new WechatSession()
                    .setOpenId(openId)
                    .setSessionKey(sessionKey);
            return session;

        } catch (IOException e) {
            e.printStackTrace();
            throw ExceptionBuilder.build(AccountErrorCode.OtherServiceWechatFailed,
                    String.format("convert response[%s] failed", resp));
        }
    }
}
