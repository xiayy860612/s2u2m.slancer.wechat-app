package com.s2u2m.slancer.account.otherService.wechat.dto;

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
public class WechatSession {
    private String openId;
    private String sessionKey;
}
