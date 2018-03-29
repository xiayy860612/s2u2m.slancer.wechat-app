package com.s2u2m.slancer.core.formatchecker;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author Amos Xia
 */
@Configuration
@PropertySource("classpath:config/format-check-config.properties")
@ConfigurationProperties(prefix = "s2u2m.format-check.password")
@Getter
@Setter
public class PasswordFormatProperty {
    private Integer minLen;
    private Integer maxLen;
}
