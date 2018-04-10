package com.s2u2m.slancer.core.time;

import com.s2u2m.slancer.test.AbCoreTest;
import org.junit.Assert;
import org.junit.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class S2u2mTimerTest extends AbCoreTest {
    @Test
    public void nowDate() throws Exception {
        ZonedDateTime expected = ZonedDateTime.now(
                ZoneId.of(S2u2mTimer.defaultZone));

        Date now = S2u2mTimer.nowDate();
        ZonedDateTime rst = ZonedDateTime.ofInstant(
                now.toInstant(), ZoneId.of(S2u2mTimer.defaultZone));

        Assert.assertEquals(expected.getHour(), rst.getHour());
    }

}