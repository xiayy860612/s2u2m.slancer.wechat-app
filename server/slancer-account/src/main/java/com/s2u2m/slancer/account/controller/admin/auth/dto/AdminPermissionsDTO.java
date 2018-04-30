package com.s2u2m.slancer.account.controller.admin.auth.dto;

import com.s2u2m.slancer.account.auth.admin.AdminPermission;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * class AdminPermissionsDTO
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminPermissionsDTO {
    private List<AdminPermission> permissions;
}
