package com.s2u2m.slancer.account.service.mobile.account.wechat;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.dao.mobile.WechatAccountDAO;
import com.s2u2m.slancer.account.entity.mobile.UserEntity;
import com.s2u2m.slancer.account.entity.mobile.WechatAccountEntity;
import com.s2u2m.slancer.account.otherService.wechat.WechatService;
import com.s2u2m.slancer.account.otherService.wechat.dto.WechatSession;
import com.s2u2m.slancer.account.service.mobile.UserService;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.cache.redis.S2u2mRedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Amos Xia
 * @date 2018/4/17
 */
@Service
public class WechatAccountService {

    private static final String WechatRegInfoCachePre = "wechat:reg";

    @Autowired
    WechatService wechatService;

    @Autowired
    WechatAccountProperty accountProperty;

    @Autowired
    WechatAccountDAO accountDAO;

    @Autowired
    UserService userService;

    @Autowired
    S2u2mRedisCache cache;

    public UserEntity login(String code) {
        WechatSession session = wechatService.js2Session(code);
        WechatAccountEntity accountEntity = accountDAO.selectByOpenId(session.getOpenId());
        if (accountEntity == null) {
            String key = cache.createKey(WechatRegInfoCachePre, code);
            cache.set(key, session, accountProperty.getRegExpireMs());

            throw ExceptionBuilder.build(AccountErrorCode.WechatAccountNotExisted,
                    "wechat account not register");
        }

        return userService.getUser(accountEntity.getUserId());
    }

    @Transactional
    public UserEntity reg(WechatRegInfo info) {
        String key = cache.createKey(WechatRegInfoCachePre, info.getCode());
        WechatSession session = cache.get(key, WechatSession.class);
        if (session == null) {
            throw ExceptionBuilder.build(AccountErrorCode.WechatJsCodeNotExisted,
                    String.format("Wechat jscode[%s] not existed", info.getCode()));
        }

        // create user
        UserEntity input = new UserEntity()
                .setNickName(info.getNickName())
                .setAvatarUrl(info.getAvatarUrl())
                .setGender(info.getGender())
                .setCity(info.getCity());
        UserEntity entity = userService.reg(input);

        // create account and bind to user
        WechatAccountEntity accountEntity = new WechatAccountEntity()
                .setOpenId(session.getOpenId())
                .setUserId(entity.getId());
        accountDAO.insert(accountEntity);

        // del reg cache
        cache.del(key);
        return entity;
    }
}
