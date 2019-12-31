package com.github.dkurata38.library.domain_lib.date_time.range;


import com.github.dkurata38.library.domain_lib.date_time.util.LocalDateTimeUtil;
import com.github.dkurata38.library.domain_lib.date_time.value.Time;

import java.time.Duration;

public final class TimeRange extends AbstractChronologyRange<TimeRange, Time> {
	public TimeRange(final Time start, final Time end) {
		super(start, end);
	}

	public Duration toDuration() {
		return Duration.between(getStart().getValue(), getEnd().getValue());
	}

	@Override
	public boolean overlaps(final TimeRange other) {
		return LocalDateTimeUtil.overlaps(getStart().getValue(), getEnd().getValue(),
				other.getStart().getValue(), other.getEnd().getValue());
	}
}
