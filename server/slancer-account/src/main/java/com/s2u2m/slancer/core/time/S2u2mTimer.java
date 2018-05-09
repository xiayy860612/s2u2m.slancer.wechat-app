package com.s2u2m.slancer.core.time;

import java.time.Instant;
import java.util.Date;
import java.util.TimeZone;

/**
 * Time always based on epoch time
 * mostly datetime are pure data, no zone info
 * you should convert datetime by specific zone
 */
public class S2u2mTimer {

    public static final String defaultZone = "GMT+8";

    static {
        TimeZone.setDefault(TimeZone.getTimeZone(defaultZone));
    }

    public static long nowMs() {
        return Instant.now().toEpochMilli();
    }

    public static Date nowDate() {
        return Date.from(Instant.now());
    }
}
