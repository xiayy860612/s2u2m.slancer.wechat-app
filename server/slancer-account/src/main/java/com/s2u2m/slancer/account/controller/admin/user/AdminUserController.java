package com.s2u2m.slancer.account.controller.admin.user;

import com.s2u2m.slancer.account.auth.admin.AdminPermissionConfig;
import com.s2u2m.slancer.account.controller.admin.user.dto.AdminUserDTO;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

/**
 * class AdminUserController
 *
 * @author xiayy860612
 * @date 2018/5/3
 */
@RestController
@RequestMapping(value = "/admin/user")
public class AdminUserController {


    @RequiresPermissions({AdminPermissionConfig.System_User_All})
    @S2u2mResponseBody
    @PostMapping(value = "/{id}/update")
    public void update(@RequestHeader("token") String token,
                       @PathVariable String id, @RequestBody AdminUserDTO info) {

    }
}
