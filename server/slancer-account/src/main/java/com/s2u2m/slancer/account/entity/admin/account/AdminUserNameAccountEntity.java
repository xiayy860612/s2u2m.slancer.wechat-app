package com.s2u2m.slancer.account.entity.admin.account;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.util.Date;

/**
 * class AdminUserNameAccountEntity
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminUserNameAccountEntity {
    private String userName;
    private String password;
    private String userId;
    private Date createTime;
}
