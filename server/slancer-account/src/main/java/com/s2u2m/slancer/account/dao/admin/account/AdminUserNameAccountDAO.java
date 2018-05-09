package com.s2u2m.slancer.account.dao.admin.account;

import com.s2u2m.slancer.account.entity.admin.account.AdminUserNameAccountEntity;
/**
 * class AdminUserNameAccountDAO
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
public interface AdminUserNameAccountDAO {
    void insert(AdminUserNameAccountEntity entity);

    Boolean exists(String userName);

    AdminUserNameAccountEntity selectByUserName(String userName);
}
