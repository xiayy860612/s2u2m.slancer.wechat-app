package com.s2u2m.slancer.account.error;

import com.s2u2m.slancer.core.exception.error.ErrorTypeEnum;
import com.s2u2m.slancer.core.exception.error.IErrorCodeEnum;

/**
 * @author Amos Xia
 * @date 2018/4/9
 */
public enum AccountServiceErrorEnum implements IErrorCodeEnum {

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
    AccountServiceErrorEnum(int code) {
        this.code = code;
    }
}
