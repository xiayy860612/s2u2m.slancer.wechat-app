package com.s2u2m.slancer.account.entity.authorization;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Amos Xia
 * @date 2018/4/28
 */
@Getter
@Setter
public class Permission {
    private Boolean allowable;
    private PermissionActionEnum action;
    private String resource;
    private String desc;
}
