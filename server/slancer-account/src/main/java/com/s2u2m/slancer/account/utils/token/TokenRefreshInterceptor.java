package com.s2u2m.slancer.account.utils.token;

import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenData;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenOp;
import com.s2u2m.slancer.core.token.ITokenOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author Amos Xia
 * @date 2018/4/10
 */
public class TokenRefreshInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    ITokenOp<MobileUserTokenData> tokenOp;

    @Override
    public boolean preHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler
    ) throws Exception {
//        String token = request.getHeader(MobileUserTokenOp.TokenKeyInHeader);
//        if (token == null) {
//            return true;
//        }
//
//        tokenOp.refresh(token);
        return true;
    }
}
