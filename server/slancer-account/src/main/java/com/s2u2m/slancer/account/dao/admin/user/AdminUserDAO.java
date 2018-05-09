package com.s2u2m.slancer.account.dao.admin.user;

import com.s2u2m.slancer.account.entity.admin.AdminUserEntity;

/**
 * interface AdminUserDAO
 *
 * @author xiayy860612
 * @date 2018/5/9
 */
public interface AdminUserDAO {
    void insert(AdminUserEntity entity);

    AdminUserEntity selectById(String id);
}
