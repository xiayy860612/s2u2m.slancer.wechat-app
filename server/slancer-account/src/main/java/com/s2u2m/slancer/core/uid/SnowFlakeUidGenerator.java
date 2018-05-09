package com.s2u2m.slancer.core.uid;

import com.s2u2m.slancer.core.exception.ExceptionBuilder;
import com.s2u2m.slancer.core.exception.error.FrameworkCoreErrorCode;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.time.Instant;

/**
 * 基于Twitter SnowFlake算法,
 * refer to https://github.com/twitter/snowflake
 *
 * Total: 64 bits
 * | 0 | 时间戳 41 bits  | 机器标识 10 bits | 随机码 12 bits |
 *
 * 1 year = 31536000000 ms
 * 2^41 ms = 2.199023256×10¹² ms = 69.7 years
 * 即可用时间段为指定时间节点后69.7年
 *
 * 随机码用于同一毫秒时间段内生成不同的id
 * 2^12 = 4096
 */
@Slf4j
public class SnowFlakeUidGenerator {

	/**
	 * 随机码占用的位数
	 */
	private static final byte SEQUENCE_BIT = 12;
	/**
	 * 机器标识占用的位数
	 */
	private static final byte MACHINE_BIT = 10;

	/**
	 * Max value.
	 */
	private static final int MAX_MACHINE_NUM = -1 ^ (-1 << MACHINE_BIT);
	private static final int MAX_SEQUENCE = -1 ^ (-1 << SEQUENCE_BIT);

	/**
	 * Bit Left Offset.
	 */
	private static final byte MACHINE_LEFT = SEQUENCE_BIT;
	private static final byte TIMESTAMP_LEFT = MACHINE_LEFT + MACHINE_BIT;

	/**
	 * 指定时间节点, 从epoch时间进行计算
	 */
	@Getter
	private long startEpochMs;
	@Getter
	private int machineId;

	private long lastTimestamp = -1L;
	private int sequence = 0;

	public SnowFlakeUidGenerator(Instant startTime, int machineId)
			throws Exception {
		this.startEpochMs = startTime.toEpochMilli();
		this.machineId = machineId & MAX_MACHINE_NUM;
		if (this.machineId == 0) {
		    throw new Exception("Machine Id touch max value");
		}
	}

	public synchronized long nextId() {
		long curTimestamp = System.currentTimeMillis();
		if (curTimestamp < lastTimestamp) {
			throw ExceptionBuilder.build(
					FrameworkCoreErrorCode.UidGenerateUidError,
					"Clock moved backwards.");
		}

		if (curTimestamp == lastTimestamp) {
			//相同毫秒内，序列号自增
			sequence = (sequence + 1) & MAX_SEQUENCE;
			//同一毫秒的序列数已经达到最大,　等待下一个时间段
			if (sequence == 0L) {
				curTimestamp = getNextMs();
			}
		} else {
			//不同毫秒内，序列号置为0
			sequence = 0;
		}

		lastTimestamp = curTimestamp;
		return (curTimestamp - this.startEpochMs) << TIMESTAMP_LEFT
			| this.machineId << MACHINE_LEFT
			| sequence;
	}

	private long getNextMs() {
		long ms = Instant.now().toEpochMilli();
		while (ms <= lastTimestamp) {
			ms = Instant.now().toEpochMilli();
		}
		return ms;
	}

	public synchronized String nextIdByString() {
		long id = nextId();
		return Long.toString(id);
	}
}
