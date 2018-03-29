package com.s2u2m.slancer.core.formatchecker;


/**
 *
 */
public class PasswordFormatChecker extends AbFormatChecker<String> {

    private static final String specialChars = "!@#$%^&*()-+=";

    private PasswordFormatProperty property;

    public PasswordFormatChecker(String data, PasswordFormatProperty property) {
        super(data);
        this.property = property;
    }

    @Override
    public boolean check() {
        if (data.length() < property.getMinLen()
                || data.length() > property.getMaxLen()) {
            return false;
        }

        String pwdPattern = String.format(
                "^[a-zA-Z]\\w+[%s]+[\\w%s]*", specialChars, specialChars);
        return data.matches(pwdPattern);
    }
}
