package com.s2u2m.slancer.account.entity.mobile;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author
 */
@Getter
@Setter
@Accessors(chain = true)
public class PhoneAccountEntity {
    private String userId;
    private String phone;
}