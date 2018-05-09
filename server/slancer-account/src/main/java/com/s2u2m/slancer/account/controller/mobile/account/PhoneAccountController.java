package com.s2u2m.slancer.account.controller.mobile.account;

import com.s2u2m.slancer.account.controller.mobile.account.dto.*;
import com.s2u2m.slancer.account.entity.mobile.UserEntity;
import com.s2u2m.slancer.account.service.mobile.account.phone.PhoneAccountService;
import com.s2u2m.slancer.account.service.mobile.account.phone.PhoneRegInfo;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenData;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenGen;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenOp;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
//@RestController
@RequestMapping(value = "/account/phone")
public class PhoneAccountController {

    @Autowired
    private PhoneAccountService phoneAccountService;

    @Autowired
    private MobileUserTokenGen tokenGen;
    @Autowired
    private MobileUserTokenOp tokenOp;

    @S2u2mResponseBody
    @GetMapping(value = "/{phone}/regCode")
    public PhoneCodeDTO getRegCode(@PathVariable String phone) {
        String code = phoneAccountService.getRegCode(phone);
        return new PhoneCodeDTO().setCode(code);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/reg")
    public LoginInfoDTO reg(@RequestBody PhoneRegInfoDTO regInfoDTO) {
        // reg
        PhoneRegInfo regInfo = new PhoneRegInfo()
                .setPhone(regInfoDTO.getPhone())
                .setCode(regInfoDTO.getCode())
                .setNickName(regInfoDTO.getNickName())
                .setPassword(regInfoDTO.getPassword())
                .setPwdConfirm(regInfoDTO.getPwdConfirm());
        UserEntity entity = phoneAccountService.reg(regInfo);

        // generate token
        MobileUserTokenData data = new MobileUserTokenData().setUserEntity(entity);
        String token = tokenGen.token(data);
        tokenOp.set(token, data);

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/pwdLogin")
    public LoginInfoDTO pwdLogin(@RequestBody PhonePwdLoginDTO loginDTO) {
        // login
        UserEntity entity = phoneAccountService.loginByPassword(
                loginDTO.getPhone(), loginDTO.getPassword());

        // generate token
        MobileUserTokenData data = new MobileUserTokenData().setUserEntity(entity);
        String token = tokenGen.token(data);
        tokenOp.set(token, data);

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

    @S2u2mResponseBody
    @GetMapping(value = "/{phone}/loginCode")
    public PhoneCodeDTO loginCode(@PathVariable String phone) {
        String code = phoneAccountService.getLoginCode(phone);
        return new PhoneCodeDTO().setCode(code);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/codeLogin")
    public LoginInfoDTO codeLogin(@RequestBody PhoneCodeLoginDTO loginDTO) {
        // login
        UserEntity entity = phoneAccountService.loginByCode(
                loginDTO.getPhone(), loginDTO.getCode());

        // generate token
        MobileUserTokenData data = new MobileUserTokenData().setUserEntity(entity);
        String token = tokenGen.token(data);
        tokenOp.set(token, data);

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

}
