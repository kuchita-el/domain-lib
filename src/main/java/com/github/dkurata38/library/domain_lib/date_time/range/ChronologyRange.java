package com.github.dkurata38.library.domain_lib.date_time.range;

import com.github.dkurata38.library.domain_lib.date_time.value.Chronology;

public interface ChronologyRange<T extends ChronologyRange<T,V>, V extends Chronology<V, ?>> {
	V getStart();
	V getEnd();

	boolean overlaps(T other);
}
