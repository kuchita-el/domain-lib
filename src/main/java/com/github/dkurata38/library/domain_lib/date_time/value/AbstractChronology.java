package com.github.dkurata38.library.domain_lib.date_time.value;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
abstract class AbstractChronology<T extends AbstractChronology<T, V>, V extends Temporal & TemporalAdjuster> implements Chronology<T, V>{
	protected final V value;

	public boolean isAfter(T other) {
		return compareTo(other) > 0;
	}

	public boolean isAfterOrEqualsTo(T other) {
		return compareTo(other) >= 0;
	}

	public boolean isBefore(T other) {
		return compareTo(other) < 0;
	}

	public boolean isBeforeOrEqualsTo(T other) {
		return compareTo(other) <= 0;
	}
}
