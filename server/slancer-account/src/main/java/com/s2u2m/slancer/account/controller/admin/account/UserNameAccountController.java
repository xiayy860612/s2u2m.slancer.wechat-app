package com.s2u2m.slancer.account.controller.admin.account;

import com.s2u2m.slancer.account.controller.admin.account.dto.AdminLoginInfoDTO;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameAccountRegDTO;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameLoginDTO;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class UserNameAccountController
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@RestController
@RequestMapping(value = "/admin/account/username")
public class UserNameAccountController {

    @S2u2mResponseBody
    @PostMapping(value = "/login")
    public AdminLoginInfoDTO login(@RequestBody UserNameLoginDTO info) {

        AdminLoginInfoDTO dto = new AdminLoginInfoDTO();
        return dto;
    }

    @S2u2mResponseBody
    @PostMapping(value = "/add")
    public void addAccount(@RequestBody UserNameAccountRegDTO info) {

    }
}

