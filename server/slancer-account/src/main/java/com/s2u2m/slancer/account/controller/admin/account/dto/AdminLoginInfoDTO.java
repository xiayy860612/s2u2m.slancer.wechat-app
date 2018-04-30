package com.s2u2m.slancer.account.controller.admin.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * class AdminLoginInfoDTO
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminLoginInfoDTO {
    private String token;
    private String nickName;
}
