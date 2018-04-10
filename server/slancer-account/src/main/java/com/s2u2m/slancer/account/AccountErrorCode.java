package com.s2u2m.slancer.account;

import com.s2u2m.slancer.core.exception.error.ErrorTypeEnum;
import com.s2u2m.slancer.core.exception.error.IErrorCodeEnum;

/**
 * @author Amos Xia
 */
public enum AccountErrorCode implements IErrorCodeEnum {


    PhoneInvalid(101),
    PasswordInvalid(102),
    PasswordNotMatched(103),
    RegCodeNotMatched(104),
    RegCodeNotExisted(105),
    PhoneAccountNotExisted(106),
    PhoneLoginCodeNotExisted(107),
    PhoneLoginCodeNotMatched(108),
    PhoneAccountExisted(109),
    ;

    @Override
    public ErrorTypeEnum getTypeEnum() {
        return ErrorTypeEnum.AccountServiceError;
    }

    @Override
    public int getCode() {
        return 0;
    }

    private int code;
    AccountErrorCode(int code) {
        this.code = code;
    }
}
