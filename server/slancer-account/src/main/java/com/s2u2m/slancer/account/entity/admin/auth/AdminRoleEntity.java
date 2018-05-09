package com.s2u2m.slancer.account.entity.admin.auth;

import lombok.Getter;
import lombok.Setter;

/**
 * class AdminRoleEntity
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@Getter
@Setter
public class AdminRoleEntity {
    private Integer id;
    private String name;
    private String permissions;
}
