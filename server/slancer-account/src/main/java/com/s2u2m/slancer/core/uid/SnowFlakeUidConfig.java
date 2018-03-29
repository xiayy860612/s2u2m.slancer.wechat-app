package com.s2u2m.slancer.core.uid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Instant;

@Configuration
class SnowFlakeUidConfig {

    @Autowired
    SnowFlakeUidProperty property;

    @Bean
    public SnowFlakeUidGenerator generator() throws Exception {
        Instant start = Instant.ofEpochMilli(property.getStartTime());
        return new SnowFlakeUidGenerator(start, property.getWorkerId());
    }
}
