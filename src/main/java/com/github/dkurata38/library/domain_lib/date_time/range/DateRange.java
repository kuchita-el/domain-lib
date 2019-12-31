package com.github.dkurata38.library.domain_lib.date_time.range;

import com.github.dkurata38.library.domain_lib.date_time.util.LocalDateTimeUtil;
import com.github.dkurata38.library.domain_lib.date_time.value.Date;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public final class DateRange extends AbstractChronologyRange<DateRange, Date> {

	public DateRange(final Date start, final Date end) {
		super(start, end);
	}

	@Override
	public boolean overlaps(final DateRange other) {
		return LocalDateTimeUtil.overlaps(
				getStart().getValue(),
				getEnd().getValue(),
				other.getStart().getValue(),
				other.getEnd().getValue()
		);
	}
}
