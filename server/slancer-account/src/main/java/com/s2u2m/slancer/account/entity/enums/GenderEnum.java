package com.s2u2m.slancer.account.entity.enums;

import com.s2u2m.slancer.core.enumhandler.IIntEnum;
import lombok.Getter;

/**
 * @author Amos Xia
 * @date 2018/4/18
 */
public enum GenderEnum implements IIntEnum {
    Unknown(0),
    Male(1),
    Female(2)
    ;

    @Getter
    private int value;
    GenderEnum(int value) {
        this.value = value;
    }

}
