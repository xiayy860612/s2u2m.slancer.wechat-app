package com.s2u2m.slancer.account.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Amos Xia
 */
@RestController
public class IndexController {

    @GetMapping(value = "/")
    public String index() {
        return "Hello world";
    }

}
