package com.s2u2m.slancer.account.mapper;

import com.s2u2m.slancer.account.entity.UserEntity;

/**
 * @author
 */
public interface UserMapper {
    void insert(UserEntity entity);

    UserEntity selectById(String id);

    void update(UserEntity entity);
}
