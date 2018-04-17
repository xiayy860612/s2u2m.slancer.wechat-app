package com.s2u2m.slancer.account.service;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.test.AbS2u2mSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class UserServiceTest extends AbS2u2mSpringTest {

    @Autowired
    UserService userService;

    @Test
    public void reg() throws Exception {
        String nickName = "hello";
        String pwd = "abc#123456";
        UserEntity input = new UserEntity()
                .setNickName(nickName)
                .setPassword(pwd);

        UserEntity entity = userService.reg(input);

        assertNotNull(entity.getId());
        assertTrue(input.getNickName().equals(entity.getNickName()));
    }

}