package com.s2u2m.slancer.account.utils.token.admin;

import com.s2u2m.slancer.core.token.ITokenGen;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * class AdminUserTokenGen
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@Component
public class AdminUserTokenGen implements ITokenGen<AdminUserTokenData> {
    @Override
    public String token(AdminUserTokenData data) {
        String token = UUID.randomUUID().toString();
        return token;
    }
}
