package com.s2u2m.slancer.core.cache.redis;

/**
 * interface IRedisKeyGen
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
public interface IRedisKeyGen<DT> {
    String key(DT data);

    default String keyParamJoin(String... params) {
        return String.join(":", params);
    }
}
