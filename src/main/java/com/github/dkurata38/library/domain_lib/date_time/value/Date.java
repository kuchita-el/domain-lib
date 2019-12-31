package com.github.dkurata38.library.domain_lib.date_time.value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public final class Date extends AbstractChronology<Date, LocalDate> {

	public Date(final LocalDate value) {
		super(value);
	}

	public DateTime at(Time time) {
		return DateTime.of(this, time);
	}

	@Override
	public String format(final DateTimeFormatter formatter) {
		return null;
	}

	@Override
	public int compareTo(final Date o) {
		return 0;
	}
}
