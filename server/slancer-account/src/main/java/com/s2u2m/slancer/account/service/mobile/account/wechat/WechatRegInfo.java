package com.s2u2m.slancer.account.service.mobile.account.wechat;

import com.s2u2m.slancer.account.entity.enums.GenderEnum;
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
public class WechatRegInfo {
    private String code;
    private String nickName;
    private String avatarUrl;
    private GenderEnum gender;
    private String city;
}
