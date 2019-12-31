package com.github.dkurata38.library.domain_lib.date_time.range;

import com.github.dkurata38.library.domain_lib.date_time.value.Chronology;
import lombok.Getter;

@Getter
public abstract class AbstractChronologyRange<T extends ChronologyRange<T, V>, V extends Chronology<V, ?>> implements ChronologyRange<T, V> {
	private final V start;
	private final V end;

	protected AbstractChronologyRange(final V start, final V end) {
		assert start.isBefore(end);
		this.start = start;
		this.end = end;
	}
}
