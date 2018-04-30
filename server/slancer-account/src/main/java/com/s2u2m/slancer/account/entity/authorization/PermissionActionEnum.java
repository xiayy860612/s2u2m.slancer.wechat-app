package com.s2u2m.slancer.account.entity.authorization;

import com.s2u2m.slancer.core.enumhandler.IIntEnum;
import lombok.Getter;

/**
 * @author Amos Xia
 * @date 2018/4/28
 */
public enum PermissionActionEnum implements IIntEnum {
    All(0),
    Create(1),
    Read(2),
    Update(3),
    Delete(4)
    ;

    @Getter
    private int value;
    PermissionActionEnum(int code) {
        this.value = code;
    }
}
