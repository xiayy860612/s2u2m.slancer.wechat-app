package com.s2u2m.slancer.core.formatchecker;

import com.s2u2m.slancer.test.AbS2u2mSpringTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class PhoneFormatCheckerTest extends AbS2u2mSpringTest{

    @Test
    public void check() throws Exception {
        String phone = "18615705064";
        PhoneFormatChecker formatChecker = new PhoneFormatChecker(phone);
        boolean rst = formatChecker.check();
        assertTrue(rst);
    }

}