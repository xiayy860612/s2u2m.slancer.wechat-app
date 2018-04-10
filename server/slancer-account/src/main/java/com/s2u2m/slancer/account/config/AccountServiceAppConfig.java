package com.s2u2m.slancer.account.config;

import com.s2u2m.slancer.account.utils.token.TokenRefreshInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Amos Xia
 * @date 2018/4/10
 */
@Configuration
public class AccountServiceAppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TokenRefreshInterceptor());
    }
}
