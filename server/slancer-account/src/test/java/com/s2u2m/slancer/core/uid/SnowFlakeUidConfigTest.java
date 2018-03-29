package com.s2u2m.slancer.core.uid;

import com.s2u2m.slancer.test.AbCoreTest;
import com.s2u2m.slancer.core.time.S2u2mTimer;
import org.junit.Test;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.Assert.*;

public class SnowFlakeUidConfigTest extends AbCoreTest {

    @Test
    public void generator_success() throws Exception {
        Instant instant = Instant.now();
        ZonedDateTime start = ZonedDateTime.ofInstant(
                instant, ZoneId.of(S2u2mTimer.defaultZone));
        long startEpochMs = instant.toEpochMilli();
        SnowFlakeUidGenerator generator = new SnowFlakeUidGenerator(instant, 1);

        assertEquals(startEpochMs, generator.getStartEpochMs());
    }

}