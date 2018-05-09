package com.s2u2m.slancer.account.controller.admin.auth;

import com.s2u2m.slancer.account.auth.admin.AdminPermissionConfig;
import com.s2u2m.slancer.account.controller.admin.auth.dto.AdminPermissionsDTO;
import com.s2u2m.slancer.account.controller.admin.auth.dto.CreateRoleDTO;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * class UnAuthController
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@RestController
@RequestMapping(value = "/admin/auth")
public class AuthController {

    @Autowired
    AdminPermissionConfig permissionConfig;

    @S2u2mResponseBody
    @GetMapping(value = "/permission/list")
    public AdminPermissionsDTO getPermissions() {
        return new AdminPermissionsDTO()
                .setPermissions(permissionConfig.getPermissions());
    }

    @S2u2mResponseBody
    @RequiresPermissions({AdminPermissionConfig.System_User_Create})
    @PostMapping(value = "/role/create")
    public void createRole(@RequestBody CreateRoleDTO dto) {

    }
}
