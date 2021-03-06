package com.s2u2m.slancer.account;

import com.s2u2m.slancer.core.exception.error.ErrorTypeEnum;
import com.s2u2m.slancer.core.exception.error.IErrorCodeEnum;
import lombok.Getter;

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

    WechatAccountNotExisted(201),
    WechatJsCodeNotExisted(202),

    OtherServiceWechatFailed(701),
    ;

    @Override
    public ErrorTypeEnum getTypeEnum() {
        return ErrorTypeEnum.AccountServiceError;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    private int code;
    AccountErrorCode(int code) {
        this.code = code;
    }
}
