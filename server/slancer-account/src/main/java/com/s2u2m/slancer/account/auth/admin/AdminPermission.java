package com.s2u2m.slancer.account.auth.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Arrays;
import java.util.List;

/**
 * class AdminPermission
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminPermission {
    private String displayName;
    private String permission;
    private List<AdminPermission> children = Arrays.asList();
}
