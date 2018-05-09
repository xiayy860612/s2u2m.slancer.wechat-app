package com.s2u2m.slancer.account.service.admin;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameAccountRegDTO;
import com.s2u2m.slancer.account.controller.admin.account.dto.UserNameLoginDTO;
import com.s2u2m.slancer.account.dao.admin.account.AdminUserNameAccountDAO;
import com.s2u2m.slancer.account.entity.admin.AdminUserEntity;
import com.s2u2m.slancer.account.entity.admin.account.AdminUserNameAccountEntity;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.formatchecker.CombineFormatChecker;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatChecker;
import com.s2u2m.slancer.core.formatchecker.PasswordFormatProperty;
import com.s2u2m.slancer.core.formatchecker.UserNameFormatChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * class AdminUserNameAccountService
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
@Service
public class AdminUserNameAccountService {

    @Autowired
    private AdminUserService userService;

    @Autowired
    private PasswordFormatProperty passwordFormatProperty;

    @Autowired
    private AdminUserNameAccountDAO accountDAO;

    @Transactional
    public AdminUserEntity reg(UserNameAccountRegDTO regDTO) {
        // check reg info
        CombineFormatChecker checker = new CombineFormatChecker(
                new UserNameFormatChecker(regDTO.getUserName()),
                new PasswordFormatChecker(regDTO.getPassword(), passwordFormatProperty)
        );
        if (!checker.check()) {
            throw ExceptionBuilder.build(AccountErrorCode.UserNameOrPasswordFormatError);
        }

        Boolean existed = accountDAO.exists(regDTO.getUserName());
        if (existed) {
            throw ExceptionBuilder.build(AccountErrorCode.SystemUserNameAccountExisted);
        }

        // create user
        AdminUserEntity userEntity = new AdminUserEntity()
                .setNickName(regDTO.getUserName());
        userEntity = userService.add(userEntity);

        // create account and bind to user
        AdminUserNameAccountEntity accountEntity = new AdminUserNameAccountEntity()
                .setUserId(userEntity.getId())
                .setUserName(regDTO.getUserName())
                .setPassword(regDTO.getPassword());
        accountDAO.insert(accountEntity);
        return userEntity;
    }

    @Transactional(readOnly = true)
    public AdminUserEntity login(UserNameLoginDTO loginDTO) {
        // get account
        AdminUserNameAccountEntity accountEntity = accountDAO.selectByUserName(loginDTO.getUsername());
        if (accountEntity == null) {
            throw ExceptionBuilder.build(AccountErrorCode.SystemUserNameAccountNotExisted);
        }

        // check
        if (!accountEntity.getPassword().equals(loginDTO.getPassword())) {
            throw ExceptionBuilder.build(AccountErrorCode.PasswordNotMatched);
        }

        // get user
        AdminUserEntity userEntity = userService.get(accountEntity.getUserId());
        return userEntity;
    }
}
