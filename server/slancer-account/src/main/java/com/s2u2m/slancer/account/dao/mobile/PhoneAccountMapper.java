package com.s2u2m.slancer.account.dao.mobile;

import com.s2u2m.slancer.account.entity.mobile.PhoneAccountEntity;

/**
 * @author
 */
public interface PhoneAccountMapper {
    int insert(PhoneAccountEntity entity);

    PhoneAccountEntity selectByPhone(String phone);
}
