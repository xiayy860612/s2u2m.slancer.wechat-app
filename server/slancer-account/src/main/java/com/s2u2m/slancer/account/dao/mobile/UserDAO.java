package com.s2u2m.slancer.account.dao.mobile;

import com.s2u2m.slancer.account.entity.mobile.UserEntity;

/**
 * @author
 */
public interface UserDAO {
    void insert(UserEntity entity);

    UserEntity selectById(String id);

    void update(UserEntity entity);
}
