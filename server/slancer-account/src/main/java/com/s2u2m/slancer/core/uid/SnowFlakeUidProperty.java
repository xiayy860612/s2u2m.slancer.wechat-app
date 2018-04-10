package com.s2u2m.slancer.core.uid;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:config/snow-flake-uid.properties")
@ConfigurationProperties(prefix = "s2u2m.uid")
@Getter
@Setter
class SnowFlakeUidProperty {
	private long startTime;
	private int workerId;
}
