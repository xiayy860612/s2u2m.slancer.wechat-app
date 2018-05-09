package com.s2u2m.slancer.account.auth.admin;

import lombok.Getter;
import org.apache.shiro.authc.AuthenticationToken;

/**
 * class AdminTokenAuth
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
public class AdminTokenAuth implements AuthenticationToken {

    @Getter
    private String token;
    public AdminTokenAuth(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return this.token;
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }
}
