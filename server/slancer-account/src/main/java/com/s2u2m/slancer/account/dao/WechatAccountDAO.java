package com.s2u2m.slancer.account.dao;

import com.s2u2m.slancer.account.entity.WechatAccountEntity;

/**
 * @author Amos Xia
 */
public interface WechatAccountDAO {
    WechatAccountEntity selectByOpenId(String openId);

    int insert(WechatAccountEntity entity);
}
