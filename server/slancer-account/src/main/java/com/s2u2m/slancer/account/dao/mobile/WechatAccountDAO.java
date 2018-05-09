package com.s2u2m.slancer.account.dao.mobile;

import com.s2u2m.slancer.account.entity.mobile.WechatAccountEntity;

/**
 * @author Amos Xia
 */
public interface WechatAccountDAO {
    WechatAccountEntity selectByOpenId(String openId);

    int insert(WechatAccountEntity entity);
}
