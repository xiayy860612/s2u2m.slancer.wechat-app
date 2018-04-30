package com.s2u2m.slancer.account.utils.token.admin;

import com.s2u2m.slancer.account.utils.token.SlancerTokenProperty;
import com.s2u2m.slancer.core.cache.redis.S2u2mRedisCache;
import com.s2u2m.slancer.core.token.ITokenOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * class AdminUserTokenOp
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@Component
public class AdminUserTokenOp implements ITokenOp<AdminUserTokenData> {

    @Autowired
    private S2u2mRedisCache redisCache;

    @Autowired
    private AdminUserTokenKeyGen keyGen;

    @Autowired
    private SlancerTokenProperty property;

    @Override
    public void set(String token, AdminUserTokenData data) {
        String key = keyGen.key(token);
        redisCache.set(key, data, property.getExpireMs());
    }

    @Override
    public AdminUserTokenData get(String token) {
        String key = keyGen.key(token);
        AdminUserTokenData data = redisCache.get(key, AdminUserTokenData.class);
        return data;
    }

    @Override
    public void del(String token) {
        String key = keyGen.key(token);
        redisCache.del(key);
    }

    @Override
    public void refresh(String token) {
        String key = keyGen.key(token);
        redisCache.refresh(key, property.getExpireMs());
    }
}
