package com.s2u2m.slancer.account.service.account.phone;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.entity.PhoneAccountEntity;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.dao.PhoneAccountMapper;
import com.s2u2m.slancer.account.service.UserService;
import com.s2u2m.slancer.core.formatchecker.PhoneFormatChecker;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.uid.SnowFlakeUidGenerator;
import com.s2u2m.slancer.core.cache.redis.S2u2mRedisCache;
import com.s2u2m.slancer.core.utils.random.RandomUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Amos Xia
 */
@Slf4j
@Service
public class PhoneAccountService {

    @Autowired
    PhoneAccountProperty config;

    @Autowired
    S2u2mRedisCache cache;

    @Autowired
    SnowFlakeUidGenerator uidGenerator;

    @Autowired
    UserService userService;

    @Autowired
    PhoneAccountMapper phoneAccountMapper;

    public String getRegCode(String phone) {
        // check format
        if (!new PhoneFormatChecker(phone).check()) {
            throw ExceptionBuilder.build(
                    AccountErrorCode.PhoneInvalid,
                    String.format("phone[%s] invalid", phone));
        }

//        PhoneAccountEntity entity = phoneAccountMapper.selectByPhone(phone);
//        if (entity != null) {
//            throw ExceptionBuilder.build(AccountErrorCode.PhoneAccountExisted,
//                    String.format("phone[%s] account already existed", phone));
//        }

        String code = RandomUtil.nextString(config.getCodeLen());
        cache.set(cache.createKey(PhoneCodeCache.RegPreKey, phone),
                new PhoneCodeCache().setPhone(phone).setCode(code),
                config.getCodeExpMs());
        return code;
    }

    @Transactional
    public UserEntity reg(PhoneRegInfo info) {
        // check format and code
        if (!new PhoneFormatChecker(info.getPhone()).check()) {
            throw ExceptionBuilder.build(
                    AccountErrorCode.PhoneInvalid,
                    String.format("phone[%s] invalid", info.getPhone()));
        }

        if (!info.getPassword().equals(info.getPwdConfirm())) {
            throw ExceptionBuilder.build(AccountErrorCode.PasswordNotMatched,
                    "reg password not matched");
        }

        String key = cache.createKey(PhoneCodeCache.RegPreKey, info.getPhone());
        PhoneCodeCache codeCache = cache.get(key, PhoneCodeCache.class);
        if (codeCache == null) {
            throw ExceptionBuilder.build(AccountErrorCode.RegCodeNotExisted,
                    "phone reg code not existed");
        }

        if (!codeCache.getCode().equals(info.getCode())) {
            throw ExceptionBuilder.build(AccountErrorCode.RegCodeNotMatched,
                    "phone reg code not correct");
        }

        // create user
        UserEntity input = new UserEntity()
                .setPassword(info.getPassword())
                .setNickName(info.getNickName());
        UserEntity entity = userService.reg(input);

        // create account
        PhoneAccountEntity accountEntity = new PhoneAccountEntity()
                .setPhone(info.getPhone())
                .setUserId(entity.getId());
        phoneAccountMapper.insert(accountEntity);

        // del cache
        cache.del(key);
        return entity;
    }

    @Transactional(readOnly = true)
    public UserEntity loginByPassword(String phone, String pwd) {
        PhoneAccountEntity entity = phoneAccountMapper.selectByPhone(phone);
        if (entity == null) {
            throw ExceptionBuilder.build(AccountErrorCode.PhoneAccountNotExisted, "" +
                    String.format("phone[%s] account not existed", phone));
        }

        UserEntity userEntity = userService.getUser(entity.getUserId());
        if (!userEntity.getPassword().equals(pwd)) {
            throw ExceptionBuilder.build(AccountErrorCode.PasswordInvalid,
                    "password invalid");
        }

        return userEntity;
    }

    public String getLoginCode(String phone) {
        // check format
        if (!new PhoneFormatChecker(phone).check()) {
            throw ExceptionBuilder.build(
                    AccountErrorCode.PhoneInvalid,
                    String.format("phone[%s] invalid", phone));
        }

        PhoneAccountEntity accountEntity = phoneAccountMapper.selectByPhone(phone);
        if (accountEntity == null) {
            throw ExceptionBuilder.build(AccountErrorCode.PhoneAccountNotExisted,
                    String.format("phone[%s] account not existed", phone));
        }

        String code = RandomUtil.nextString(config.getCodeLen());
        cache.set(cache.createKey(PhoneCodeCache.LoginPreKey, phone),
                new PhoneCodeCache().setPhone(phone).setCode(code),
                config.getCodeExpMs());
        return code;
    }

    @Transactional(readOnly = true)
    public UserEntity loginByCode(String phone, String code) {
        String key = cache.createKey(PhoneCodeCache.LoginPreKey, phone);
        PhoneCodeCache codeCache = cache.get(key, PhoneCodeCache.class);
        if (codeCache == null) {
            throw ExceptionBuilder.build(AccountErrorCode.PhoneLoginCodeNotExisted,
                    "phone login code not existed");
        }

        if(!codeCache.getCode().equals(code)) {
            throw ExceptionBuilder.build(AccountErrorCode.PhoneLoginCodeNotMatched,
                    "phone login code not matched");
        }

        PhoneAccountEntity accountEntity = phoneAccountMapper.selectByPhone(phone);
        if (accountEntity == null) {
            throw ExceptionBuilder.build(AccountErrorCode.PhoneAccountNotExisted,
                    String.format("phone[%s] account not existed", phone));
        }

        UserEntity userEntity = userService.getUser(accountEntity.getUserId());
        cache.del(key);
        return userEntity;
    }
}
