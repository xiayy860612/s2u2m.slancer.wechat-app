package com.s2u2m.slancer.account.service.account;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Amos Xia
 */
@Configuration
@PropertySource("classpath:config/account-config.properties")
@ConfigurationProperties(prefix = "s2u2m.account.phone")
@Getter
@Setter
public class PhoneAccountProperty {
    private Integer codeLen;
    private Long codeExpMs;
}
