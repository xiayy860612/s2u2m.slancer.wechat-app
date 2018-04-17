package com.s2u2m.slancer.account.controller.account;

import com.s2u2m.slancer.account.controller.account.dto.LoginInfoDTO;
import com.s2u2m.slancer.account.controller.account.dto.PhoneCodeDTO;
import com.s2u2m.slancer.account.controller.account.dto.PhoneRegInfoDTO;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.service.account.phone.PhoneAccountService;
import com.s2u2m.slancer.account.service.account.phone.PhoneRegInfo;
import com.s2u2m.slancer.account.utils.token.SlancerTokenData;
import com.s2u2m.slancer.core.token.ITokenOp;
import com.s2u2m.slancer.test.AbS2u2mSpringTest;
import org.junit.Test;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.Assert.*;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doReturn;

public class PhoneAccountControllerTest extends AbS2u2mSpringTest {

    @MockBean
    private PhoneAccountService phoneAccountService;

    @MockBean
    private ITokenOp<SlancerTokenData> tokenOp;

    @Test
    public void getRegCode() throws Exception {
        String code = "123456";
        doReturn(code).when(phoneAccountService).getRegCode(anyString());

        PhoneCodeDTO rst = this.httpGet(
                "/account/phone/18615705064/regCode", PhoneCodeDTO.class);

        assertTrue(code.equals(rst.getCode()));
    }

    @Test
    public void reg() throws Exception {
        String nickName = "hello world";
        String pwd = "123456";

        UserEntity exp = new UserEntity()
                .setNickName(nickName);
        doReturn(exp).when(phoneAccountService).reg(any(PhoneRegInfo.class));

        String token = "123456";
        doReturn(token).when(tokenOp).token(any(SlancerTokenData.class));

        PhoneRegInfoDTO regInfoDTO = new PhoneRegInfoDTO()
                .setPhone("18615705064")
                .setCode("123456")
                .setNickName(nickName)
                .setPassword(pwd)
                .setPwdConfirm(pwd);
        LoginInfoDTO loginInfoDTO = this.httpPost(
                "/account/phone/reg", regInfoDTO,
                LoginInfoDTO.class);

        assertNotNull(loginInfoDTO.getToken());
        assertTrue(token.equals(loginInfoDTO.getToken()));
        assertTrue(nickName.equals(loginInfoDTO.getInfo().getNickName()));
    }

    @Test
    public void pwdLogin() throws Exception {
    }

    @Test
    public void loginCode() throws Exception {
    }

    @Test
    public void codeLogin() throws Exception {
    }

}