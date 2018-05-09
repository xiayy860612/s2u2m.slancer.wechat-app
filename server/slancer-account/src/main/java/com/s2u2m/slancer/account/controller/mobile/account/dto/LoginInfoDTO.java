package com.s2u2m.slancer.account.controller.mobile.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author
 */
@Getter
@Setter
@Accessors(chain = true)
public class LoginInfoDTO {
    private String token;
    private UserInfoDTO info;
}