package com.s2u2m.slancer.account.utils.token.admin;

import com.s2u2m.slancer.core.token.AbTokenData;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * class AdminUserTokenData
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminUserTokenData extends AbTokenData {
    private String id;
}
