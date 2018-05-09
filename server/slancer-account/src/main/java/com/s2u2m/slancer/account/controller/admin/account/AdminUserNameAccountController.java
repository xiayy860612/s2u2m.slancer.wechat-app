package com.s2u2m.slancer.account.controller.admin.account;

import com.s2u2m.slancer.account.controller.admin.account.dto.AdminLoginInfoDTO;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameAccountRegDTO;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameLoginDTO;
import com.s2u2m.slancer.account.entity.admin.AdminUserEntity;
import com.s2u2m.slancer.account.service.admin.AdminUserNameAccountService;
import com.s2u2m.slancer.account.utils.token.admin.AdminUserTokenData;
import com.s2u2m.slancer.account.utils.token.admin.AdminUserTokenGen;
import com.s2u2m.slancer.account.utils.token.admin.AdminUserTokenOp;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class AdminUserNameAccountController
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@RestController
@RequestMapping(value = "/admin/account/username")
public class AdminUserNameAccountController {

    @Autowired
    AdminUserNameAccountService accountService;

    @Autowired
    AdminUserTokenGen tokenGen;

    @Autowired
    AdminUserTokenOp tokenOp;

    @S2u2mResponseBody
    @PostMapping(value = "/reg")
    public AdminLoginInfoDTO reg(@RequestBody UserNameAccountRegDTO info) {
        AdminUserEntity userEntity = accountService.reg(info);
        return login(userEntity);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/login")
    public AdminLoginInfoDTO login(@RequestBody UserNameLoginDTO info) {
        AdminUserEntity userEntity = accountService.login(info);
        return login(userEntity);
    }

    private AdminLoginInfoDTO login(AdminUserEntity userEntity) {
        AdminUserTokenData tokenData = new AdminUserTokenData()
                .setId(userEntity.getId());
        String token = tokenGen.token(tokenData);
        tokenOp.set(token, tokenData);

        AdminLoginInfoDTO dto = new AdminLoginInfoDTO()
                .setToken(token)
                .setNickName(userEntity.getNickName());
        return dto;
    }

}

