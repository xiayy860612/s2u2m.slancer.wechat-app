package com.s2u2m.slancer.core.utils.random;

import java.util.Random;

public class RandomUtil {

    private static final String chars = "abcdefghijklmnopqrstuvwxyz"
            + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "1234567890";

    public static String nextString(int len) {
        Random rand = new Random();
        String rst = "";
        int index = 0;
        while(len > 0) {
            index = rand.nextInt(chars.length());
            rst += chars.charAt(index);
            --len;
        }
        return rst;
    }
}
