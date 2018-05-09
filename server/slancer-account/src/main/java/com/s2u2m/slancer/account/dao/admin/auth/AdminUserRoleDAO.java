package com.s2u2m.slancer.account.dao.admin.auth;

import com.s2u2m.slancer.account.entity.admin.auth.AdminUserRoleEntity;

import java.util.List;

/**
 * interface AdminUserRoleDAO
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
public interface AdminUserRoleDAO {
    List<AdminUserRoleEntity> selectByUserId(String id);
}
