package com.s2u2m.slancer.account.mapper;

import com.s2u2m.slancer.account.entity.PhoneAccountEntity;

/**
 * @author
 */
public interface PhoneAccountMapper {
    int insert(PhoneAccountEntity entity);

    PhoneAccountEntity selectByPhone(String phone);
}
