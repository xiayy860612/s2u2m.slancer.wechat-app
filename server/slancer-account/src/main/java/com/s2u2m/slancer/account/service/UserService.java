package com.s2u2m.slancer.account.service;

import com.s2u2m.slancer.account.entity.UserEntity;
import com.s2u2m.slancer.account.mapper.UserMapper;
import com.s2u2m.slancer.core.time.S2u2mTimer;
import com.s2u2m.slancer.core.uid.SnowFlakeUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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

    public UserEntity reg(UserEntity input) {
        String id = uidGenerator.nextIdByString();
        input.setId(id).setCreateTime(S2u2mTimer.nowDate());
        userMapper.insert(input);
        return input;
    }

    public void updateUserInfo(UserEntity input) {
        throw new NotImplementedException();
    }

    public UserEntity getUser(String id) {
        throw new NotImplementedException();
    }

}
