package com.s2u2m.slancer.account.utils.token;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.core.token.AbTokenData;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Amos Xia
 */
@Getter
@Setter
@Accessors(chain = true)
public class SlancerTokenData extends AbTokenData{
    private UserEntity userEntity;
}
