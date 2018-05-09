package com.s2u2m.slancer.core.formatchecker;

/**
 * class UserNameFormatChecker
 *
 * @author xiayy860612
 * @date 2018/5/7
 */
public final class UserNameFormatChecker extends AbFormatChecker<String> {

    public UserNameFormatChecker(String data) {
        super(data);
    }

    @Override
    public boolean check() {
        return true;
    }
}
