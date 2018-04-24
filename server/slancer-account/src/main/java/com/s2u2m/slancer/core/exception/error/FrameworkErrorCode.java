package com.s2u2m.slancer.core.exception.error;

public enum FrameworkErrorCode implements IErrorCodeEnum {
    Unknown(0),
    ComponentError(1),

    // uid error: 1XX
    UidGenerateUidError(101),

    ResponseBuildError(111),

    IntEnumNotExisted(150),
    ;

    @Override
    public ErrorTypeEnum getTypeEnum() {
        return ErrorTypeEnum.FrameworkError;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    private int code = 0;
    FrameworkErrorCode(int code) {
        this.code = code;
    }
}
