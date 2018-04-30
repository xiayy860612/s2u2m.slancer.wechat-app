package com.s2u2m.slancer.account.auth.admin;

import lombok.Getter;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * class AdminPermissionConfig
 *
 * @author xiayy860612
 * @date 2018/5/4
 */
@Configuration
public class AdminPermissionConfig {
    public static String All = "*";

    // system module
    public static final String System_All = "system:*";
    public static final String System_User_All = "system:user:*";
    public static final String System_User_Create = "system:user:create";
    public static final String System_User_Read = "system:user:read";
    public static final String System_User_Update = "system:user:update";
    public static final String System_User_Del = "system:user:del";


    @Getter
    private final List<AdminPermission> permissions = new ArrayList<>();

    @PostConstruct
    public void init() {
        // load into cache
        permissions.add(systemPermission());

    }

    private AdminPermission systemPermission() {
        List<AdminPermission> sysChildren = new ArrayList<>();
        AdminPermission sysPermissionNode = new AdminPermission()
                .setDisplayName("系统").setPermission(System_All)
                .setChildren(sysChildren);

        // sys user config
        List<AdminPermission> sysUserChildren = new ArrayList<>();
        AdminPermission sysUserNode = new AdminPermission()
                .setDisplayName("用户").setPermission(System_User_All)
                .setChildren(sysUserChildren);
        sysChildren.add(sysUserNode);

        AdminPermission sysUserCreate = new AdminPermission()
                .setDisplayName("新建用户").setPermission(System_User_Create);
        sysUserChildren.add(sysUserCreate);

        AdminPermission sysUserRead = new AdminPermission()
                .setDisplayName("获取用户信息").setPermission(System_User_Read);
        sysUserChildren.add(sysUserRead);

        AdminPermission sysUserUpdate = new AdminPermission()
                .setDisplayName("修改用户").setPermission(System_User_Update);
        sysUserChildren.add(sysUserUpdate);

        AdminPermission sysUserDel = new AdminPermission()
                .setDisplayName("删除用户").setPermission(System_User_Del);
        sysUserChildren.add(sysUserDel);

        return sysPermissionNode;
    }

}
