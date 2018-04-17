package com.s2u2m.slancer.account.service;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.dao.UserDAO;
import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatChecker;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatProperty;
import com.s2u2m.slancer.core.time.S2u2mTimer;
import com.s2u2m.slancer.core.uid.SnowFlakeUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author
 */
@Service
public class UserService {

    @Autowired
    SnowFlakeUidGenerator uidGenerator;

    @Autowired
    UserDAO userDAO;

    @Autowired
    PasswordFormatProperty passwordFormatProperty;

    @Transactional
    public UserEntity reg(UserEntity input) {
        if (input.getPassword() != null) {
            boolean rst = !new PasswordFormatChecker(
                    input.getPassword(), passwordFormatProperty)
                    .check();
            if (!rst) {
                throw ExceptionBuilder.build(
                        AccountErrorCode.PasswordInvalid,
                        "password invalid");
            }
        }

        String id = uidGenerator.nextIdByString();
        input.setId(id).setCreateTime(S2u2mTimer.nowDate());
        userDAO.insert(input);
        return input;
    }

    @Transactional
    public UserEntity updateUserInfo(UserEntity input) {
        input.setUpdateTime(S2u2mTimer.nowDate());
        userDAO.update(input);
        return input;
    }

    @Transactional(readOnly = true)
    public UserEntity getUser(String id) {
        return userDAO.selectById(id);
    }

}
