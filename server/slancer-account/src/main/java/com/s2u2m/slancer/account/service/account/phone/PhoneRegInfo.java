package com.s2u2m.slancer.account.service.account.phone;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author
 */
@Getter
@Setter
@Accessors(chain = true)
public class PhoneRegInfo {
    private String phone;
    private String code;

    private String nickName;
    private String password;
    private String pwdConfirm;
}
