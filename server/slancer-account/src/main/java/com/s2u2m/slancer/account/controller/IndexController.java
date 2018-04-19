package com.s2u2m.slancer.account.controller;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.exception.S2u2mSpringException;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amos Xia
 */
@RestController
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "Hello world";
    }

    @PostMapping(value = "/error/code")
    public Integer getErrorValue(@RequestBody AccountErrorCode code) {
        S2u2mSpringException exception = ExceptionBuilder.build(code, "");
        return exception.getErrCode();
    }
}
