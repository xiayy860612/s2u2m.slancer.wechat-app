package com.s2u2m.slancer.account.utils.token.mobile;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.utils.token.SlancerTokenProperty;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenData;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenKeyGen;
import com.s2u2m.slancer.core.token.ITokenOp;
import com.s2u2m.slancer.core.cache.redis.S2u2mRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author Amos Xia
 */
@Component
public class MobileUserTokenOp implements ITokenOp<MobileUserTokenData> {

    @Autowired
    private S2u2mRedisCache cache;

    @Autowired
    private MobileUserTokenKeyGen keyGen;

    @Autowired
    private SlancerTokenProperty property;

    @Override
    public void set(String token, MobileUserTokenData data) {
        String key = keyGen.key(token);
        cache.set(key, data.getUserEntity(), property.getExpireMs());
    }

    @Override
    public MobileUserTokenData get(String token) {
        String key = keyGen.key(token);
        MobileUserTokenData data = cache.get(key, MobileUserTokenData.class);
        return data;
    }

    @Override
    public void del(String token) {

    }

    @Override
    public void refresh(String token) {
        String key = keyGen.key(token);
        cache.refresh(key, property.getExpireMs());
    }
}
