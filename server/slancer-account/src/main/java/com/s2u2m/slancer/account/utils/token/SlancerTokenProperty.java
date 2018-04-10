package com.s2u2m.slancer.account.utils.token;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author Amos Xia
 * @date 2018/4/10
 */
@Component
@PropertySource("classpath:config/token.properties")
@ConfigurationProperties(prefix = "s2u2m.token")
@Getter
@Setter
public class SlancerTokenProperty {
    private Long expireMs;
}
