package com.s2u2m.slancer.account.service.account;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Amos Xia
 */
@Getter
@Setter
@Accessors(chain = true)
public class PhoneCodeCache {
    public static final String RegPreKey = "phone_reg";
    public static final String LoginPreKey = "phone_login";

    private String phone;
    private String code;
}
