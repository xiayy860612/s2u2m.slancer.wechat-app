package com.s2u2m.slancer.account.service.admin;

import com.s2u2m.slancer.account.dao.admin.user.AdminUserDAO;
import com.s2u2m.slancer.account.entity.admin.AdminUserEntity;
import com.s2u2m.slancer.core.time.S2u2mTimer;
import com.s2u2m.slancer.core.uid.SnowFlakeUidGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class AdminUserService
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@Service
public class AdminUserService {

    @Autowired
    private SnowFlakeUidGenerator uidGenerator;

    @Autowired
    private AdminUserDAO userDAO;

    @Transactional
    public AdminUserEntity add(AdminUserEntity entity) {
        String id = uidGenerator.nextIdByString();
        entity.setId(id).setCreateTime(S2u2mTimer.nowDate());
        userDAO.insert(entity);
        return entity;
    }

    @Transactional(readOnly = true)
    public AdminUserEntity get(String id) {
        return userDAO.selectById(id);
    }
}
