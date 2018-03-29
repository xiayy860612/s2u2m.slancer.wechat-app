package com.s2u2m.slancer.account.controller.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author
 */
@Getter
@Setter
@Accessors(chain = true)
public class PhoneRegInfoDTO {
    private String phone;
    private String code;
    private String nickName;
    private String password;
    private String pwdConfirm;
}
