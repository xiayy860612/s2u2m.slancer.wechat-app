package com.s2u2m.slancer.account.otherService.wechat;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Amos Xia
 * @date 2018/4/17
 */
@Configuration
@PropertySource("classpath:other-service-config/wechat.properties")
@ConfigurationProperties(prefix = "s2u2m.otherService.wechat")
@Getter
@Setter
public class WechatServiceProperty {
    private String appId;
    private String secret;
}
