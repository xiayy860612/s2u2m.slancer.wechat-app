package com.s2u2m.slancer.account.controller.account.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author Amos Xia
 * @date 2018/4/17
 */
@Getter
@Setter
@Accessors(chain = true)
public class WechatRegInfoDTO {
    private String wechatCode;
    private String nickName;
    private String avatarUrl;
    private Integer gender;
    private String city;
}
