package com.s2u2m.slancer.account.controller.account;

import com.s2u2m.slancer.account.controller.account.dto.*;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.service.account.phone.PhoneAccountService;
import com.s2u2m.slancer.account.service.account.phone.PhoneRegInfo;
import com.s2u2m.slancer.account.utils.token.SlancerTokenData;
import com.s2u2m.slancer.core.token.ITokenOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author
 */
@RestController
@RequestMapping(value = "/account/phone")
public class PhoneAccountController {

    @Autowired
    private PhoneAccountService phoneAccountService;

    @Autowired
    private ITokenOp<SlancerTokenData> tokenOp;

    @GetMapping(value = "/{phone}/regCode")
    public PhoneCodeDTO getRegCode(@PathVariable String phone) {
        String code = phoneAccountService.getRegCode(phone);
        return new PhoneCodeDTO().setCode(code);
    }

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
        String token = tokenOp.token(
                new SlancerTokenData().setUserEntity(entity));

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

    @PostMapping(value = "/pwdLogin")
    public LoginInfoDTO pwdLogin(@RequestBody PhonePwdLoginDTO loginDTO) {
        // login
        UserEntity entity = phoneAccountService.loginByPassword(
                loginDTO.getPhone(), loginDTO.getPassword());

        // generate token
        String token = tokenOp.token(
                new SlancerTokenData().setUserEntity(entity));

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

    @GetMapping(value = "/{phone}/loginCode")
    public PhoneCodeDTO loginCode(@PathVariable String phone) {
        String code = phoneAccountService.getLoginCode(phone);
        return new PhoneCodeDTO().setCode(code);
    }

    @PostMapping(value = "/codeLogin")
    public LoginInfoDTO codeLogin(@RequestBody PhoneCodeLoginDTO loginDTO) {
        // login
        UserEntity entity = phoneAccountService.loginByCode(
                loginDTO.getPhone(), loginDTO.getCode());

        // generate token
        String token = tokenOp.token(
                new SlancerTokenData().setUserEntity(entity));

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

}
