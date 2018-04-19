package com.s2u2m.slancer.account.controller.account.dto;

import com.s2u2m.slancer.account.entity.enums.GenderEnum;
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
    private String avatarUrl;
    private GenderEnum gender;
    private String city;
}
