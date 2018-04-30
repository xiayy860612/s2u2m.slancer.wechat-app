package com.s2u2m.slancer.account.entity.admin.account;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * class UserNameAdminAccountEntity
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@Getter
@Setter
public class UserNameAdminAccountEntity {
    private String username;
    private String password;
    private Date createTime;
}
