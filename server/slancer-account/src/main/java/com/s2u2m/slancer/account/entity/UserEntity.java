package com.s2u2m.slancer.account.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * @author
 */
@Getter
@Setter
@Accessors(chain = true)
public class UserEntity {
    private String id;
    private String nickName;
    private String password;
    private Date createTime;
    private Date updateTime;
    private Boolean deleteFlag = false;
}
