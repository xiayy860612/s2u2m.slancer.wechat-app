package com.s2u2m.slancer.account.controller.admin.auth.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * class CreateRoleDTO
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@Getter
@Setter
public class CreateRoleDTO {
    private String roleName;
    private List<String> permissions;
}
