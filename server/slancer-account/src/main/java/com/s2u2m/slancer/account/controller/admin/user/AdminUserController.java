package com.s2u2m.slancer.account.controller.admin.user;

import com.s2u2m.slancer.account.controller.admin.user.dto.AdminUserDTO;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
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

    @S2u2mResponseBody
    @PostMapping(value = "/{id}/update")
    public void update(@PathVariable String id, @RequestBody AdminUserDTO info) {

    }
}
