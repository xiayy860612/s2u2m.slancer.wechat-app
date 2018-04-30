package com.s2u2m.slancer.account.entity.admin.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * class AdminUserRoleEntity
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
@Getter
@Setter
public class AdminUserRoleEntity {
    private String userId;
    private AdminRoleEntity role;
}
