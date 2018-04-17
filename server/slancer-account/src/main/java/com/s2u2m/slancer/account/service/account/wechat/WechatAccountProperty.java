package com.s2u2m.slancer.account.service.account.wechat;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Amos Xia
 * @date 2018/4/17
 */
@Configuration
@PropertySource("classpath:config/account-config.properties")
@ConfigurationProperties(prefix = "s2u2m.account.wechat")
@Getter
@Setter
@Accessors(chain = true)
public class WechatAccountProperty {
    private Long regExpireMs;

}
