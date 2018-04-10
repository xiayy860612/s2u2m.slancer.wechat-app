package com.s2u2m.slancer.core.exception.error;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {

    NoError(0),
    FrameworkError(1),

    /**
     * Internal Error <= 100, customized error > 100
     */
    AccountServiceError(101)
    ;

    public static final int TYPE_LF_SHIFT = 20;

    private int type = 0;
    ErrorTypeEnum(int type) {
        this.type = type;
    }
}
