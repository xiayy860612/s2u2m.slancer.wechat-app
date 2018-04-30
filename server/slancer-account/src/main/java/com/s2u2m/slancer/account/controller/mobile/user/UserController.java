package com.s2u2m.slancer.account.controller.mobile.user;

import com.s2u2m.slancer.account.controller.mobile.user.dto.UpdateUserInfoDTO;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amos Xia
 * @date 2018/4/10
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @S2u2mResponseBody
    @PostMapping(value = "/{id}")
    public void updateInfo(@PathVariable String id, @RequestBody UpdateUserInfoDTO info) {

    }

}
