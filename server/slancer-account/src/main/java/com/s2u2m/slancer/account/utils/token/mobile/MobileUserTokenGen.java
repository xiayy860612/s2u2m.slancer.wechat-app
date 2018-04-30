package com.s2u2m.slancer.account.utils.token.mobile;

import com.s2u2m.slancer.core.token.ITokenGen;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * class MobileUserTokenGen
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@Component
public class MobileUserTokenGen implements ITokenGen<MobileUserTokenData> {
    @Override
    public String token(MobileUserTokenData data) {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
