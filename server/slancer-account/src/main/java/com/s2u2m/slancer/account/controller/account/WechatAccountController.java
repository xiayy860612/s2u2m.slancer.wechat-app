package com.s2u2m.slancer.account.controller.account;

import com.s2u2m.slancer.account.controller.account.dto.LoginInfoDTO;
import com.s2u2m.slancer.account.controller.account.dto.UserInfoDTO;
import com.s2u2m.slancer.account.controller.account.dto.WechatRegInfoDTO;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.entity.enums.GenderEnum;
import com.s2u2m.slancer.account.service.account.wechat.WechatAccountService;
import com.s2u2m.slancer.account.service.account.wechat.WechatRegInfo;
import com.s2u2m.slancer.account.utils.token.SlancerTokenData;
import com.s2u2m.slancer.core.enumhandler.IntEnumParser;
import com.s2u2m.slancer.core.serialization.S2u2mResponseBody;
import com.s2u2m.slancer.core.token.ITokenOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Amos Xia
 * @date 2018/4/17
 */
@RestController
@RequestMapping(value = "/account/wechat")
public class WechatAccountController {

    @Autowired
    private WechatAccountService accountService;

    @Autowired
    private ITokenOp<SlancerTokenData> tokenOp;

    @S2u2mResponseBody
    @PostMapping(value = "/{wechatCode}/login")
    public LoginInfoDTO login(@PathVariable String wechatCode) {

        UserEntity entity = accountService.login(wechatCode);

        // generate token
        String token = tokenOp.token(
                new SlancerTokenData().setUserEntity(entity));

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName())
                .setAvatarUrl(entity.getAvatarUrl())
                .setGender(entity.getGender())
                .setCity(entity.getCity());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }

    @S2u2mResponseBody
    @PostMapping(value = "/reg")
    public LoginInfoDTO reg(@RequestBody WechatRegInfoDTO info) {

        GenderEnum gender = info.getGender() == null ?
                GenderEnum.Unknown
                : IntEnumParser.convert(info.getGender().intValue(), GenderEnum.class);
        WechatRegInfo regInfo = new WechatRegInfo()
                .setCode(info.getWechatCode())
                .setNickName(info.getNickName())
                .setAvatarUrl(info.getAvatarUrl())
                .setGender(gender)
                .setCity(info.getCity());
        UserEntity entity = accountService.reg(regInfo);

        // generate token
        String token = tokenOp.token(
                new SlancerTokenData().setUserEntity(entity));

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName())
                .setAvatarUrl(entity.getAvatarUrl())
                .setGender(entity.getGender())
                .setCity(entity.getCity());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }
}
