package com.s2u2m.slancer.account.controller.mobile.account;

import com.s2u2m.slancer.account.controller.mobile.account.dto.LoginInfoDTO;
import com.s2u2m.slancer.account.controller.mobile.account.dto.UserInfoDTO;
import com.s2u2m.slancer.account.controller.mobile.account.dto.WechatRegInfoDTO;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.entity.enums.GenderEnum;
import com.s2u2m.slancer.account.service.account.wechat.WechatAccountService;
import com.s2u2m.slancer.account.service.account.wechat.WechatRegInfo;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenData;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenGen;
import com.s2u2m.slancer.account.utils.token.mobile.MobileUserTokenOp;
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
    private MobileUserTokenGen tokenGen;
    @Autowired
    private MobileUserTokenOp tokenOp;

    @S2u2mResponseBody
    @PostMapping(value = "/{wechatCode}/login")
    public LoginInfoDTO login(@PathVariable String wechatCode) {

        UserEntity entity = accountService.login(wechatCode);

        // generate token
        MobileUserTokenData data = new MobileUserTokenData().setUserEntity(entity);
        String token = tokenGen.token(data);
        tokenOp.set(token, data);

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
        MobileUserTokenData data = new MobileUserTokenData().setUserEntity(entity);
        String token = tokenGen.token(data);
        tokenOp.set(token, data);

        UserInfoDTO userInfoDTO = new UserInfoDTO()
                .setId(entity.getId())
                .setNickName(entity.getNickName())
                .setAvatarUrl(entity.getAvatarUrl())
                .setGender(entity.getGender())
                .setCity(entity.getCity());
        return new LoginInfoDTO().setToken(token).setInfo(userInfoDTO);
    }
}
