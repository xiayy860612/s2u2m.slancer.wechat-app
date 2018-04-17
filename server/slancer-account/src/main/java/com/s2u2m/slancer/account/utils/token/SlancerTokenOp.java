package com.s2u2m.slancer.account.utils.token;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.core.token.ITokenOp;
import com.s2u2m.slancer.core.utils.cache.redis.S2u2mRedisCache;

import java.util.UUID;

/**
 * @author Amos Xia
 */
public class SlancerTokenOp implements ITokenOp<SlancerTokenData> {

    public static final String TokenKeyInHeader = "token";
    private static final String CacheKeyPre = "token";

    private S2u2mRedisCache cache;
    private SlancerTokenProperty property;

    public SlancerTokenOp(S2u2mRedisCache cache, SlancerTokenProperty property) {
        this.cache = cache;
        this.property = property;
    }

    @Override
    public String token(SlancerTokenData data) {
        String token = UUID.randomUUID().toString();
        String key = cache.createKey(CacheKeyPre, token);
        cache.set(key, data.getUserEntity(), property.getExpireMs());
        return token;
    }

    @Override
    public SlancerTokenData data(String token) {
        String key = cache.createKey(CacheKeyPre, token);
        UserEntity entity = cache.get(key, UserEntity.class);
        return new SlancerTokenData().setUserEntity(entity);
    }

    @Override
    public void refresh(String token) {
        String key = cache.createKey(CacheKeyPre, token);
        cache.refresh(key, property.getExpireMs());
    }
}
