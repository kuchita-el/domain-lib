package com.github.dkurata38.library.domain_lib.date_time.range;

import com.github.dkurata38.library.domain_lib.date_time.value.DateTime;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class DateTimeRange extends AbstractChronologyRange<DateTimeRange, DateTime> {

	public DateTimeRange(final DateTime start, final DateTime end) {
		super(start, end);
	}

	@Override
	public boolean overlaps(final DateTimeRange other) {
		return false;
	}
}
