package com.s2u2m.slancer.account.utils.token;

import com.s2u2m.slancer.core.token.ITokenOp;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Amos Xia
 */
@Configuration
public class SlancerTokenConfig {

    @Bean
    ITokenOp<SlancerTokenData> tokenOp() {
        return new SlancerTokenOp();
    }
}
