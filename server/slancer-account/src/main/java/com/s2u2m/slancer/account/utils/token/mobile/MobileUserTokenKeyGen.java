package com.s2u2m.slancer.account.utils.token.mobile;

import com.s2u2m.slancer.core.cache.redis.IRedisKeyGen;
import org.springframework.stereotype.Component;

/**
 * class MobileUserTokenKeyGen
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
@Component
public class MobileUserTokenKeyGen implements IRedisKeyGen<String> {
    @Override
    public String key(String token) {
        return keyParamJoin("token", "mobile", token);
    }
}
