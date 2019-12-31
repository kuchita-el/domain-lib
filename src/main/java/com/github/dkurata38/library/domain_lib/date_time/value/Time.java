package com.github.dkurata38.library.domain_lib.date_time.value;

import com.github.dkurata38.library.domain_lib.date_time.range.TimeRange;
import lombok.NonNull;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public final class Time extends AbstractChronology<Time, LocalTime> {
	public Time(final LocalTime value) {
		super(value);
	}

	@Override
	public String format(final DateTimeFormatter formatter) {
		return value.format(formatter);
	}

	public DateTime at(@NonNull Date date) {
		return DateTime.of(date, this);
	}

	public Time withHour(final int hour) {
		return new Time(getValue().withHour(hour));
	}

	public Time withMinute(final int minute) {
		return new Time(getValue().withMinute(minute));
	}

	public Time withSecond(final int second) {
		return new Time(getValue().withSecond(second));
	}

	public Time withNano(final int nanoOfSecond) {
		return new Time(getValue().withNano(nanoOfSecond));
	}

	public Time plusHours(final long hoursToAdd) {
		return new Time(getValue().plusHours(hoursToAdd));
	}

	public Time plusMinutes(final long minutesToAdd) {
		return new Time(getValue().plusMinutes(minutesToAdd));
	}

	public Time plusSeconds(final long secondstoAdd) {
		return new Time(getValue().plusSeconds(secondstoAdd));
	}

	public Time plusNanos(final long nanosToAdd) {
		return new Time(getValue().plusNanos(nanosToAdd));
	}

	public Time minusHours(final long hoursToSubtract) {
		return new Time(getValue().minusHours(hoursToSubtract));
	}

	public Time minusMinutes(final long minutesToSubtract) {
		return new Time(getValue().minusMinutes(minutesToSubtract));
	}

	public Time minusSeconds(final long secondsToSubtract) {
		return new Time(getValue().minusSeconds(secondsToSubtract));
	}

	public Time minusNanos(final long nanosToSubtract) {
		return new Time(getValue().minusNanos(nanosToSubtract));
	}

	public TimeRange to(Time other) {
		return new TimeRange(this, other);
	}

	@Override
	public int compareTo(final Time o) {
		return value.compareTo(o.value);
	}
}
