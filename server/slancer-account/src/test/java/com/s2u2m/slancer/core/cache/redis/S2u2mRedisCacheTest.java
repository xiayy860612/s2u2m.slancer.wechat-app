package com.s2u2m.slancer.core.cache.redis;

import com.s2u2m.slancer.test.AbS2u2mSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class S2u2mRedisCacheTest extends AbS2u2mSpringTest {

    @Autowired
    S2u2mRedisCache cache;

    @Test
    public void cachePutGet() {
        String key = "hello";
        String value = "world";

        cache.set(key, value, 3000);

        String actual = cache.get(key, String.class);
        assertTrue(value.equals(actual));
    }

}