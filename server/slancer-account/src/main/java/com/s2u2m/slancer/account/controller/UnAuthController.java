package com.s2u2m.slancer.account.controller;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.exception.S2u2mSpringException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * class UnAuthController
 *
 * @author xiayy860612
 * @date 2018/5/9
 */
@RestController
public class UnAuthController {

    @GetMapping(value = "/unauth")
    public void unAuth() {
        throw ExceptionBuilder.build(AccountErrorCode.UserNotLogin, "pls login first");
    }
}
