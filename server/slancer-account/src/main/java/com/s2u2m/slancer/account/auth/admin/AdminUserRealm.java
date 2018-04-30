package com.s2u2m.slancer.account.auth.admin;

import com.s2u2m.slancer.account.AccountErrorCode;
import com.s2u2m.slancer.account.dao.admin.AdminUserRoleDAO;
import com.s2u2m.slancer.account.entity.admin.auth.AdminUserRoleEntity;
import com.s2u2m.slancer.account.utils.token.admin.AdminUserTokenData;
import com.s2u2m.slancer.account.utils.token.admin.AdminUserTokenOp;
import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * class AdminUserRealm
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
@Component
public class AdminUserRealm extends AuthorizingRealm {

    @Autowired
    private AdminUserTokenOp tokenOp;

    @Autowired
    private AdminUserRoleDAO userRoleDAO;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof AdminTokenAuth;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        AdminUserTokenData tokenData = (AdminUserTokenData) principalCollection.getPrimaryPrincipal();
        // get role permission
        List<AdminUserRoleEntity> userRoleEntities = userRoleDAO.selectByUserId(tokenData.getId());
        List<String> permissions = userRoleEntities.stream().flatMap(entity -> {
            String rolePermissionsJoin = entity.getRole().getPermissions();
            return Stream.of(rolePermissionsJoin.split("|"));
        }).collect(Collectors.toList());

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken authenticationToken) throws AuthenticationException {
        AdminTokenAuth auth = (AdminTokenAuth) authenticationToken;
        AdminUserTokenData tokenData = tokenOp.get(auth.getToken());
        if (tokenData == null) {
            throw ExceptionBuilder.build(AccountErrorCode.UserNotLogin, "pls login first");
        }

        return new SimpleAuthenticationInfo(tokenData, null, getName());
    }
}
