package com.github.dkurata38.library.domain_lib.date_time.value;

import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@EqualsAndHashCode(callSuper = true)
public final class DateTime extends AbstractChronology<DateTime, LocalDateTime> {

	public DateTime(@NonNull final LocalDateTime value) {
		super(value);
	}

	@Override
	public String format(@NonNull final DateTimeFormatter formatter) {
		return value.format(formatter);
	}

	@Override
	public int compareTo(@NonNull final DateTime o) {
		return value.compareTo(o.value);
	}

	public Date toDate() {
		return new Date(value.toLocalDate());
	}

	public Time toTime() {
		return new Time(value.toLocalTime());
	}

	public static DateTime of(Date date, Time time) {
		return new DateTime(LocalDateTime.of(date.value, time.value));
	}
}
