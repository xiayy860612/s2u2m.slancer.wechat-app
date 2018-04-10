package com.s2u2m.slancer.account.utils.token;

import com.s2u2m.slancer.core.token.ITokenOp;
import com.s2u2m.slancer.core.utils.cache.redis.S2u2mRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author Amos Xia
 */
@Configuration
public class SlancerTokenConfig {

    @Autowired
    S2u2mRedisCache cache;

    @Autowired
    SlancerTokenProperty property;

    @Bean
    ITokenOp<SlancerTokenData> tokenOp() {
        return new SlancerTokenOp(cache, property);
    }
}
