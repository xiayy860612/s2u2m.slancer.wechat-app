package com.s2u2m.slancer.core.formatchecker;


/**
 * @author Amos Xia
 */
public class PhoneFormatChecker extends AbFormatChecker<String> {

    public PhoneFormatChecker(String data) {
        super(data);
    }

    @Override
    public boolean check() {
        String pattern = "^(\\+86)*1([3587]|47)\\w+$";
        return data.matches(pattern);
    }
}
