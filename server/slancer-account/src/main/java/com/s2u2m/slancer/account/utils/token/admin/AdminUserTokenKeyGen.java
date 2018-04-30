package com.s2u2m.slancer.account.utils.token.admin;

import com.s2u2m.slancer.core.cache.redis.IRedisKeyGen;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * class AdminUserTokenKeyGen
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@Component
public class AdminUserTokenKeyGen implements IRedisKeyGen<String> {
    private static final String KeyPre = "";

    @Override
    public String key(String token) {
        return keyParamJoin("token", "admin", token);
    }
}
