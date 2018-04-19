package com.s2u2m.slancer.core.enumhandler;

import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.exception.error.FrameworkErrorCode;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author Amos Xia
 * @date 2018/4/18
 */
public class IntEnumParser {

    public static <ET extends Enum<ET> & IIntEnum> ET convert(
            int value, Class<ET> etClass) {
        Optional<ET> optional = Stream.of(etClass.getEnumConstants())
                .filter(et -> et.getValue() == value)
                .findAny();
        if(!optional.isPresent()) {
            throw ExceptionBuilder.build(FrameworkErrorCode.IntEnumNotExisted,
                    String.format("%d not existed in Enum[%s]", value, etClass.getName()));
        }

        return optional.get();
    }
}
