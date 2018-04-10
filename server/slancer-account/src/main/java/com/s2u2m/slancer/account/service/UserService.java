package com.s2u2m.slancer.account.service;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.mapper.UserMapper;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatChecker;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatProperty;
import com.s2u2m.slancer.core.time.S2u2mTimer;
import com.s2u2m.slancer.core.uid.SnowFlakeUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.sql.Date;

/**
 * @author
 */
@Service
public class UserService {

    @Autowired
    SnowFlakeUidGenerator uidGenerator;

    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordFormatProperty passwordFormatProperty;

    @Transactional
    public UserEntity reg(UserEntity input) {
        if (!new PasswordFormatChecker(input.getPassword(), passwordFormatProperty)
                .check()) {
            throw ExceptionBuilder.build(
                    AccountErrorCode.PasswordInvalid,
                    "password invalid");
        }

        String id = uidGenerator.nextIdByString();
        input.setId(id).setCreateTime(S2u2mTimer.nowDate());
        userMapper.insert(input);
        return input;
    }

    @Transactional
    public UserEntity updateUserInfo(UserEntity input) {
        input.setUpdateTime(S2u2mTimer.nowDate());
        userMapper.update(input);
        return input;
    }

    @Transactional(readOnly = true)
    public UserEntity getUser(String id) {
        return userMapper.selectById(id);
    }

}
