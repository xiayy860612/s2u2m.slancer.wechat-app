package com.s2u2m.slancer.core.exception;

import com.s2u2m.slancer.core.exception.error.ErrorTypeEnum;
import com.s2u2m.slancer.core.exception.error.IErrorCodeEnum;

public class ExceptionBuilder {

    public static <ET extends IErrorCodeEnum> S2u2mSpringException build(
            ET errCode, String errMsg) {
        int code = errCode.getTypeEnum().getType() << ErrorTypeEnum.TYPE_LF_SHIFT
                | errCode.getCode();
        S2u2mSpringException exception = new S2u2mSpringException();
        exception.setErrCode(code);
        exception.setErrMsg(errMsg);
        return exception;
    }

    public static <ET extends IErrorCodeEnum> S2u2mSpringException build(
            ET errCode) {
        return build(errCode, errCode.toString());
    }
}
