package com.s2u2m.slancer.account.service.account;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.service.account.phone.PhoneAccountService;
import com.s2u2m.slancer.account.service.account.phone.PhoneCodeCache;
import com.s2u2m.slancer.account.service.account.phone.PhoneRegInfo;
import com.s2u2m.slancer.core.utils.cache.redis.S2u2mRedisCache;
import com.s2u2m.slancer.test.AbS2u2mSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

public class PhoneAccountServiceTest extends AbS2u2mSpringTest {

    @Autowired
    PhoneAccountService accountService;

    @MockBean
    S2u2mRedisCache cache;

    @Test
    public void reg() throws Exception {
        String pwd = "abc#123456";
        String nickName = "hello";
        String code = "123456";
        String phone = "18615705064";

        PhoneCodeCache expCodeCache = new PhoneCodeCache()
                .setPhone(phone)
                .setCode(code);
        doReturn(expCodeCache).when(cache).get(anyString(), any());

        PhoneRegInfo regInfo = new PhoneRegInfo()
                .setPhone(phone)
                .setPassword(pwd)
                .setPwdConfirm(pwd)
                .setNickName(nickName)
                .setCode(code);
        UserEntity userEntity = accountService.reg(regInfo);

        assertNotNull(userEntity.getId());
        assertTrue(nickName.equals(userEntity.getNickName()));
    }

    @Test
    public void loginByPassword() throws Exception {
    }

    @Test
    public void loginByCode() throws Exception {
    }

}