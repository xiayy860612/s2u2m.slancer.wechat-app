package com.s2u2m.slancer.core.cache.redis;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.exception.error.FrameworkCoreErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author Amos Xia
 */
@Component
public class S2u2mRedisCache {

    private static final String split = ":";

    @Autowired
    RedisTemplate<String, String> redisTemplate;

    public String createKey(String pre, String uq) {
        return String.join(split, pre, uq);
    }

    public <VT> void set(String key, VT value, long expireMs) {
        ObjectMapper mapper = new ObjectMapper();
        String rv;
        try {
            rv = mapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw ExceptionBuilder.build(
                    FrameworkCoreErrorCode.ComponentError,
                    e.getMessage());
        }

        redisTemplate.opsForValue().set(key, rv, expireMs, TimeUnit.MILLISECONDS);
    }

    public <VT> VT get(String key, Class<VT> vtClass) {
        String rv = redisTemplate.opsForValue().get(key);
        if (rv == null) {
            return null;
        }

        ObjectMapper mapper = new ObjectMapper();
        try {
            VT value = mapper.readValue(rv, vtClass);
            return value;
        } catch (IOException e) {
            e.printStackTrace();
            throw ExceptionBuilder.build(
                    FrameworkCoreErrorCode.ComponentError,
                    e.getMessage());
        }
    }

    public void del(String key) {
        redisTemplate.delete(key);
    }

    public void refresh(String key, long expireMs) {
        redisTemplate.expire(key, expireMs, TimeUnit.MILLISECONDS);
    }
}
