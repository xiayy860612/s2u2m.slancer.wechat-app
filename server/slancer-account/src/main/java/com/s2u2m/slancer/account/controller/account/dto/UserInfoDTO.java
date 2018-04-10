package com.s2u2m.slancer.account.controller.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author  Amos Xia
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserInfoDTO {
    private String id;
    private String nickName;
}
