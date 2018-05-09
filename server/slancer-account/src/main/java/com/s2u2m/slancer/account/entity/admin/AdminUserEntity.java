package com.s2u2m.slancer.account.entity.admin;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * class AdminUserEntity
 *
 * @author xiayy860612
 * @date 2018/5/2
 */
@Getter
@Setter
@Accessors(chain = true)
public class AdminUserEntity {
    private String id;
    private String nickName;
    private Date createTime;
    private Date updateTime;
    private Boolean deleteFlag = false;
}
