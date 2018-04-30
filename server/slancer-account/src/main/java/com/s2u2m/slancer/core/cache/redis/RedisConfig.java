package com.s2u2m.slancer.core.cache.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Amos Xia
 */
@Configuration
public class RedisConfig {

    @Bean
    RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
        RedisTemplate<String, String> redisTemplate
                = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

}
