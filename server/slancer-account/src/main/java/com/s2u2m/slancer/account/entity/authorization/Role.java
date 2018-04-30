package com.s2u2m.slancer.account.entity.authorization;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author Amos Xia
 * @date 2018/4/28
 */
@Getter
@Setter
public class Role {
    private Integer id;
    private String name;
    private List<Permission> permissions;
}
