package com.s2u2m.slancer.core.exception.error;

import lombok.Getter;

@Getter
public enum ErrorTypeEnum {

    NoError(0),
    FrameworkCoreError(1),

    /**
     * Internal Error <= 100, customized error > 100
     */
    AccountServiceError(101),
    AdminAccountServiceError(102),

    // based on TYPE_LF_SHIFT, 2^(32 - TYPE_LF_SHIFT) - 1
    MaxServiceError(4095)
    ;

    // max error code, 2^TYPE_LF_SHIFT - 1
    public static final int TYPE_LF_SHIFT = 20;

    private int type = 0;
    ErrorTypeEnum(int type) {
        this.type = type;
    }
}
